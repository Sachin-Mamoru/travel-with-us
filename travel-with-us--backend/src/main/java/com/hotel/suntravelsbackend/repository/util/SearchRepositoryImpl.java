package com.hotel.suntravelsbackend.repository.util;

import com.hotel.suntravelsbackend.model.dao.RoomAdultCount;
import com.hotel.suntravelsbackend.model.entity.Contract;
import com.hotel.suntravelsbackend.model.entity.RoomType;
import com.hotel.suntravelsbackend.repository.SearchRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Contract> searchContracts( Date checkInDate, Date checkOutDate, List<RoomAdultCount> roomAdultCountList )
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contract> criteriaQuery = criteriaBuilder.createQuery( Contract.class );
        Root<Contract> fromContractTbl = criteriaQuery.from( Contract.class );
        Join<Contract,RoomType> contractRoomTypeJoin = fromContractTbl.join( "roomTypes", JoinType.INNER );
        List<Predicate> selectedRoomList = new ArrayList<>();

        int roomCount = 0;
        for( RoomAdultCount roomAdultCount : roomAdultCountList )
        {
            roomCount += roomAdultCount.getNoOfRooms();
            selectedRoomList.add( criteriaBuilder.greaterThanOrEqualTo( contractRoomTypeJoin.get( "maxAdults" ), roomAdultCount.getNoOfAdults() ) );
            selectedRoomList.add( criteriaBuilder.greaterThanOrEqualTo( contractRoomTypeJoin.get( "noOfRooms" ), roomAdultCount.getNoOfAdults() ) );
        }

        criteriaQuery.select( fromContractTbl ).distinct( ( true ) )
                     .where( criteriaBuilder.and( selectedRoomList.toArray( new Predicate[0] ) ),
                             criteriaBuilder.and( criteriaBuilder.greaterThanOrEqualTo( fromContractTbl.get( "endDate" ), checkOutDate ) ),
                             criteriaBuilder.and( criteriaBuilder.lessThanOrEqualTo( fromContractTbl.get( "startDate" ), checkInDate ) ) );

        criteriaQuery.groupBy( fromContractTbl.get( "contractId" ) );
        criteriaQuery.having( criteriaBuilder.greaterThanOrEqualTo( criteriaBuilder.sum( contractRoomTypeJoin.get( "noOfRooms" ) ), roomCount ) );

        TypedQuery<Contract> contractListQuery = entityManager.createQuery( criteriaQuery );

        return contractListQuery.getResultList();

    }
}

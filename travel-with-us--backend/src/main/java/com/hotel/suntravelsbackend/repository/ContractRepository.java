package com.hotel.suntravelsbackend.repository;

import com.hotel.suntravelsbackend.model.dao.RoomAdultCount;
import com.hotel.suntravelsbackend.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContractRepository extends SearchRepository, JpaRepository<Contract,Long>
{
    List<Contract> searchContracts( Date checkInDate, Date checkOutDate, List<RoomAdultCount> roomAdultCountList );

    List<Contract> findAllByNameIgnoreCaseContaining(String name);
}

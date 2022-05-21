package com.hotel.suntravelsbackend.service;

import com.hotel.suntravelsbackend.model.dao.ContractHotelRoomType;
import com.hotel.suntravelsbackend.model.dao.RoomAdultCount;
import com.hotel.suntravelsbackend.model.dao.SearchCriteria;
import com.hotel.suntravelsbackend.model.dto.ContractDto;
import com.hotel.suntravelsbackend.model.dto.RoomTypeDto;
import com.hotel.suntravelsbackend.model.entity.Contract;
import com.hotel.suntravelsbackend.model.entity.Hotel;
import com.hotel.suntravelsbackend.model.entity.RoomType;
import com.hotel.suntravelsbackend.repository.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ContractService
{
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomTypeService roomTypeService;

    private static final Logger log = ( Logger ) LoggerFactory.getLogger( ContractService.class );

    /**
     * @param contractHotelRoomType - contract, hotel, room type list
     * @return - saved contract
     */
    public Contract saveContract( ContractHotelRoomType contractHotelRoomType )
    {

        Hotel hotel = hotelService.saveHotel( contractHotelRoomType.getHotel() );
        Contract contract = contractHotelRoomType.getContract();
        contract.setHotel( hotel );
        Contract savedContract = contractRepository.save( contract );
        List<RoomType> roomTypes = contractHotelRoomType.getRoomTypes();
        for( RoomType type : roomTypes )
        {
            type.setContract( savedContract );
            roomTypeService.saveRoomType( type );
        }
        return savedContract;
    }

    /**
     * +
     *
     * @return - List of contracts
     */
    public List<Contract> getAll()
    {
        return contractRepository.findAll();
    }

    /**
     * @param searchCriteria - checkInDate, number of nights, room adults count
     * @return - List of contracts with possible room types and prices
     */
    public List<ContractDto> search( SearchCriteria searchCriteria )
    {

        int noOfNights = searchCriteria.getNoOfNights();

        Calendar c = Calendar.getInstance();
        c.setTime( searchCriteria.getCheckInDate() );
        c.add( Calendar.DATE, searchCriteria.getNoOfNights() );
        Date checkOutTime = c.getTime();

        List<Contract> contractList = contractRepository.searchContracts( searchCriteria.getCheckInDate(), checkOutTime, searchCriteria.getRoomAdultCount() );
        log.info( "Retrieved contract list {}", contractList );

        List<RoomAdultCount> criteriaList = searchCriteria.getRoomAdultCount();
        //sort room adult count list in asc
        criteriaList.sort( ( o1, o2 ) ->
        {
            if( o1.getNoOfAdults() == o2.getNoOfAdults() )
                return 0;
            return o1.getNoOfAdults() < o2.getNoOfAdults() ? -1 : 1;
        } );
        List<ContractDto> contractDtoList = new ArrayList<>();
        for( Contract contract : contractList )
        {
            List<RoomType> roomTypes = contract.getRoomTypes();
            //total price of the room
            double sum = 0;

            //sort room types based on number of rooms in asc
            roomTypes.sort( ( o1, o2 ) ->
            {
                if( o1.getMaxAdults() == o2.getMaxAdults() )
                    return 0;
                return o1.getMaxAdults() < o2.getMaxAdults() ? -1 : 1;
            } );
            List<RoomAdultCount> roomAdultCounts = new ArrayList<>();
            for( RoomAdultCount roomAdultCount : criteriaList )
            {
                RoomAdultCount roomAdultCountMock = new RoomAdultCount( roomAdultCount.getNoOfRooms(), roomAdultCount.getNoOfAdults() );
                roomAdultCounts.add( roomAdultCountMock );
            }
            List<RoomTypeDto> contractRoomTypeDtoList = new ArrayList<>();
            int i = 0;
            int x = 0;
            boolean in;
            int roomAdultCountsSize = roomAdultCounts.size();
            while( i < roomTypes.size() )
            {
                in = false;
                RoomType roomType = roomTypes.get( i );
                while( x < roomAdultCountsSize )
                {
                    in = true;

                    RoomAdultCount roomAdultCount = roomAdultCounts.get( x );
                    if( roomAdultCount.getNoOfAdults() <= roomType.getMaxAdults() )
                    {
                        if( roomType.getNoOfRooms() > roomAdultCount.getNoOfRooms() )
                        {
                            double roomPrice = roomType.getPrice() * noOfNights * roomAdultCount.getNoOfAdults() * roomAdultCount.getNoOfRooms();
                            RoomTypeDto roomTypeDto = new RoomTypeDto( roomType.getRoomTypeId(), roomType.getTypeName(), roomType.getPrice(), roomType.getMaxAdults(), roomType.getNoOfRooms(), roomPrice, roomAdultCount.getNoOfAdults(), roomAdultCount.getNoOfRooms() );
                            x++;
                            if( roomType.getNoOfRooms() - roomAdultCount.getNoOfRooms() > 0 )
                            {
                                roomType.setNoOfRooms( roomType.getNoOfRooms() - roomAdultCount.getNoOfRooms() );
                            }
                            contractRoomTypeDtoList.add( roomTypeDto );
                            sum += roomPrice;
                        }
                        else
                        {
                            double roomPrice = roomType.getPrice() * noOfNights * roomAdultCount.getNoOfAdults() * roomType.getNoOfRooms();
                            RoomTypeDto roomTypeDto = new RoomTypeDto( roomType.getRoomTypeId(), roomType.getTypeName(), roomType.getPrice(), roomType.getMaxAdults(), roomType.getNoOfRooms(), roomPrice, roomAdultCount.getNoOfAdults(), roomType.getNoOfRooms() );
                            contractRoomTypeDtoList.add( roomTypeDto );
                            sum += roomPrice;
                            roomAdultCounts.get( x ).setNoOfRooms( roomAdultCount.getNoOfRooms() - roomType.getNoOfRooms() );
                            roomType.setNoOfRooms( 0 );
                            if( roomAdultCount.getNoOfRooms() == roomType.getNoOfRooms() )
                            {
                                x++;
                            }
                            break;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                if( !in )
                {
                    RoomTypeDto roomTypeDto = new RoomTypeDto( roomType.getRoomTypeId(), roomType.getTypeName(), roomType.getPrice(), roomType.getMaxAdults(), roomType.getNoOfRooms(), 0, 0, 0 );
                    contractRoomTypeDtoList.add( roomTypeDto );
                }
                i++;
            }
            double totalPrice = sum + ( sum * ( ( double ) contract.getMarkup() / 100 ) );
            ContractDto contractDto = new ContractDto( contract.getContractId(), contract.getName(), contract.getStartDate(), contract.getEndDate(), contract.getMarkup(), contract.getDescription(), contract.getType(), contract.getHotel(), contractRoomTypeDtoList, totalPrice );
            contractDtoList.add( contractDto );
        }

        //sort resulting contract list by total price
        contractDtoList.sort( ( o1, o2 ) ->
        {
            if( o1.getRoomTotalPrice() == o2.getRoomTotalPrice() )
                return 0;
            return o1.getRoomTotalPrice() < o2.getRoomTotalPrice() ? -1 : 1;
        } );
        return contractDtoList;
    }

    public List<Contract> searchContractByName( String searchkey )
    {
        searchkey = searchkey.toLowerCase();

        return contractRepository.findAllByNameIgnoreCaseContaining( searchkey );
    }
}

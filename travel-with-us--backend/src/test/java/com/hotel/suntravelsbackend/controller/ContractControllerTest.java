package com.hotel.suntravelsbackend.controller;

import com.hotel.suntravelsbackend.model.dao.ContractHotelRoomType;
import com.hotel.suntravelsbackend.model.entity.Contract;
import com.hotel.suntravelsbackend.model.entity.Hotel;
import com.hotel.suntravelsbackend.model.entity.PhoneNo;
import com.hotel.suntravelsbackend.model.entity.RoomType;
import com.hotel.suntravelsbackend.service.ContractService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
class ContractControllerTest{

    @Autowired
    private ContractService service;


    @Test
    void getContracts()
    {
        Date date1 = new Date();
        Date date2 = new Date();
        Contract dummyContract = new Contract( "Dummy contract 1", date1, date2, 15, "Good Contract", "Legal", null, null );
        List<PhoneNo> dummyPhoneNumbers = new ArrayList<PhoneNo>();
        dummyPhoneNumbers.add( new PhoneNo( "56151" ) );
        dummyPhoneNumbers.add( new PhoneNo( "1332161" ) );
        Hotel dummyHotel = new Hotel( "Dummy Hotel", "Dummy Address", dummyPhoneNumbers, "abc@xyz.com" );
        List<RoomType> dummyRoomTypes = new ArrayList<RoomType>();
        dummyRoomTypes.add( new RoomType( 1, "Sea view", 15, 5, 2, null ) );
        dummyRoomTypes.add( new RoomType( 2, "Bed view", 15, 2, 1, null ) );
        ContractHotelRoomType contractHotelRoomType = new ContractHotelRoomType( dummyContract, dummyHotel, dummyRoomTypes );

        service.saveContract( contractHotelRoomType );
        assertEquals( 1, service.getAll().size() );
    }

    @Test
    void postContract()
    {
        Date date1 = new Date();
        Date date2 = new Date();
        Contract dummyContract = new Contract( "Dummy contract 1", date1, date2, 15, "Good Contract", "Legal", null, null );
        List<PhoneNo> dummyPhoneNumbers = new ArrayList<PhoneNo>();
        dummyPhoneNumbers.add( new PhoneNo( "56151" ) );
        dummyPhoneNumbers.add( new PhoneNo( "1332161" ) );
        Hotel dummyHotel = new Hotel( "Dummy Hotel", "Dummy Address", dummyPhoneNumbers, "abc@xyz.com" );
        Hotel savedDummyHotel = new Hotel( 1, "Dummy Hotel", "Dummy Address", dummyPhoneNumbers, "abc@xyz.com" );
        List<RoomType> dummyRoomTypes = new ArrayList<RoomType>();
        dummyRoomTypes.add( new RoomType( 1, "Sea view", 15, 5, 2, null ) );
        dummyRoomTypes.add( new RoomType( 2, "Bed view", 15, 2, 1, null ) );
        ContractHotelRoomType contractHotelRoomType = new ContractHotelRoomType( dummyContract, dummyHotel, dummyRoomTypes );

        Contract savedContract = new Contract( 1, "Dummy contract 1", date1, date2, 15, "Good Contract", "Legal", savedDummyHotel, null );

        assertEquals( savedContract, service.saveContract( contractHotelRoomType ) );
    }
}
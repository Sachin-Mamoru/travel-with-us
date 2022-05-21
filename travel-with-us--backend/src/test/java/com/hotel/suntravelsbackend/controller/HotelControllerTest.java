package com.hotel.suntravelsbackend.controller;

import com.hotel.suntravelsbackend.model.entity.Hotel;
import com.hotel.suntravelsbackend.model.entity.PhoneNo;
import com.hotel.suntravelsbackend.service.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
class HotelControllerTest
{
    @Autowired( required = true )
    private HotelService service;

    @Test
    void getHotels()
    {
        List<PhoneNo> phoneNumbers = new ArrayList<>();
        phoneNumbers.add( new PhoneNo( "56151" ) );
        phoneNumbers.add( new PhoneNo( "1332161" ) );
        Hotel hotel1 = new Hotel( "Dummy Hotel 1", "Dummy Address", phoneNumbers, "abc@xyz.com" );
        Hotel hotel2 = new Hotel( "Dummy Hotel 2", "Dummy Address", phoneNumbers, "abc@xyz.com" );

        service.saveHotel( hotel1 );
        service.saveHotel( hotel2 );

        assertEquals( 2, service.findAllHotels().size() );
    }
}
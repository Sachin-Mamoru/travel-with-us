package com.hotel.suntravelsbackend.controller;

import com.hotel.suntravelsbackend.model.entity.Hotel;
import com.hotel.suntravelsbackend.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( "/api" )
public class HotelController
{
    private static final Logger log = LoggerFactory.getLogger( HotelController.class );

    @Autowired
    private HotelService hotelService;

    /**
     *
     * @return - all the hotels in the database as a list.
     */
    @GetMapping( value = "/hotels" )
    public ResponseEntity<List<Hotel>> getHotels()
    {
        try
        {
            log.info( "Current saved Hotels returned!" );
            return new ResponseEntity<>( hotelService.findAllHotels(), HttpStatus.OK );
        }
        catch( NoSuchElementException e )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }

    /**
     *
     * @param id - hotel id
     * @return - hotel with a particular id
     */
    @GetMapping( value = "/hotel/{id}" )
    public ResponseEntity<Hotel> getHotel( @PathVariable( "id" ) Long id )
    {
        try
        {
            log.info( "Get hotel by id" );
            return new ResponseEntity<>( hotelService.findOneHotel( id ), HttpStatus.OK );
        }
        catch( NoSuchElementException e )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }

    /**
     *
     * @param key - search key
     * @return - list of hotels based on name
     */
    @GetMapping( value = "/hotels/{key}" )
    public ResponseEntity<List<Hotel>> searchHotelsByName( @PathVariable( "key" ) String key )
    {
        try
        {
            log.info( "Hotels searched by name returned!" );
            return new ResponseEntity<>( hotelService.searchHotelByName( key ), HttpStatus.OK );
        }
        catch( NoSuchElementException e )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }
}

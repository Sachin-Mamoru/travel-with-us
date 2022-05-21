package com.hotel.suntravelsbackend.controller;

import com.hotel.suntravelsbackend.model.dao.ContractHotelRoomType;
import com.hotel.suntravelsbackend.model.dao.SearchCriteria;
import com.hotel.suntravelsbackend.model.dto.ContractDto;
import com.hotel.suntravelsbackend.model.entity.Contract;
import com.hotel.suntravelsbackend.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( "/api" )
public class ContractController
{
    private static final Logger logger = LoggerFactory.getLogger( ContractController.class );

    @Autowired
    private ContractService contractService;

    /**
     *
     * @param contractHotelRoomType - contract, hotel, list of room types
     * @return - saved contract
     */
    @PostMapping( value = "/contract" )
    public ResponseEntity<Contract> postContract( @RequestBody @Valid ContractHotelRoomType contractHotelRoomType )
    {
        try
        {
            Contract savedContract = contractService.saveContract( contractHotelRoomType );

            logger.info( "new Contract added {}", savedContract );
            return new ResponseEntity<>( savedContract, HttpStatus.CREATED );
        }
        catch( NoSuchElementException e )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }

    /**
     *
     * @return - All the contracts in the database as a list
     */
    @GetMapping( value = "/contracts" )
    public ResponseEntity<List<Contract>> getContracts()
    {
        try
        {
            List<Contract> contracts = contractService.getAll();
            logger.info( "Current saved contracts returned!" );
            return new ResponseEntity<>( contracts, HttpStatus.OK );
        }
        catch( NoSuchElementException e )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }

    /**
     *
     * @param searchCriteria - check in date, number of nights, room adult count
     * @return - list of contracts with possible room types and prices
     */
    @PostMapping( value = "/search" )
    public ResponseEntity<List<ContractDto>> searchContracts( @RequestBody @Valid SearchCriteria searchCriteria )
    {
        try
        {
            List<ContractDto> searchResults = contractService.search( searchCriteria );
            logger.info( "Search based on criteria: {}", searchResults );
            return new ResponseEntity<>( searchResults, HttpStatus.OK );
        }
        catch( Exception e )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }

    /**
     *
     * @param key - search key
     * @return - list of contracts based on the contract name
     */
    @GetMapping( value = "/contracts/{key}" )
    public ResponseEntity<List<Contract>> searchContractsByName( @PathVariable( "key" ) String key )
    {
        try
        {
            List<Contract> contracts = contractService.searchContractByName( key );
            logger.info( "Contracts searched by name returned!" );
            return new ResponseEntity<>( contracts, HttpStatus.OK );
        }
        catch( NoSuchElementException e )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }
}

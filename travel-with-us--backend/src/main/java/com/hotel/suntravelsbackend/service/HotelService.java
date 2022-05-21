package com.hotel.suntravelsbackend.service;

import com.hotel.suntravelsbackend.model.entity.Hotel;
import com.hotel.suntravelsbackend.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService
{
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel( Hotel hotel )
    {
        return hotelRepository.save( hotel );
    }

    public List<Hotel> findAllHotels()
    {
        return hotelRepository.findAll();
    }

    public Hotel findOneHotel( Long id )
    {
        return hotelRepository.findById( id ).orElse( null );
    }

    /**
     *
     * @param searchkey
     * @return - List of hotels
     */
    public List<Hotel> searchHotelByName( String searchkey){
        searchkey = searchkey.toLowerCase();

        return hotelRepository.findAllByHotelNameIgnoreCaseContaining( searchkey );
    }
}

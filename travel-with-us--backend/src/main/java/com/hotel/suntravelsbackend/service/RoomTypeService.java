package com.hotel.suntravelsbackend.service;

import com.hotel.suntravelsbackend.model.entity.RoomType;
import com.hotel.suntravelsbackend.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService
{
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    /**
     *
     * @param roomType
     * @return
     */
    public RoomType saveRoomType( RoomType roomType )
    {
        return roomTypeRepository.save( roomType );
    }
}

package com.hotel.suntravelsbackend.repository;

import com.hotel.suntravelsbackend.model.dao.RoomAdultCount;
import com.hotel.suntravelsbackend.model.entity.Contract;

import java.util.Date;
import java.util.List;

public interface SearchRepository
{
    public List<Contract> searchContracts( Date checkInDate ,Date checkOutDate, List<RoomAdultCount> roomAdultCountList );
}

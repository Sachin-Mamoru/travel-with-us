package com.hotel.suntravelsbackend.model.dao;

import com.hotel.suntravelsbackend.model.entity.Contract;
import com.hotel.suntravelsbackend.model.entity.Hotel;
import com.hotel.suntravelsbackend.model.entity.RoomType;

import java.util.List;

public class ContractHotelRoomType
{
    private Contract contract;
    private Hotel hotel;
    private List<RoomType> roomTypes;

    public ContractHotelRoomType( Contract contract, Hotel hotel, List<RoomType> roomTypes )
    {
        this.contract = contract;
        this.hotel = hotel;
        this.roomTypes = roomTypes;
    }

    public Contract getContract()
    {
        return contract;
    }

    public Hotel getHotel()
    {
        return hotel;
    }

    public List<RoomType> getRoomTypes()
    {
        return roomTypes;
    }

    public void setContract( Contract contract )
    {
        this.contract = contract;
    }

    public void setHotel( Hotel hotel )
    {
        this.hotel = hotel;
    }

    public void setRoomTypes( List<RoomType> roomTypes )
    {
        this.roomTypes = roomTypes;
    }
}

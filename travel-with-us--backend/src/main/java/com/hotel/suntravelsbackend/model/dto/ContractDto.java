package com.hotel.suntravelsbackend.model.dto;

import com.hotel.suntravelsbackend.model.entity.Hotel;

import java.util.Date;
import java.util.List;

public class ContractDto
{
    private long contractId;
    private String name;
    private Date startDate;
    private Date endDate;
    private int markup;
    private String description;
    private String type;
    private Hotel hotel;
    private List<RoomTypeDto> roomTypeDtos;
    private double roomTotalPrice;

    public ContractDto( long contractId, String name, Date startDate, Date endDate, int markup, String description, String type, Hotel hotel, List<RoomTypeDto> roomTypeDtos, double roomTotalPrice )
    {
        this.contractId = contractId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.markup = markup;
        this.description = description;
        this.type = type;
        this.hotel = hotel;
        this.roomTypeDtos = roomTypeDtos;
        this.roomTotalPrice = roomTotalPrice;
    }

    public long getContractId()
    {
        return contractId;
    }

    public void setContractId( long contractId )
    {
        this.contractId = contractId;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate( Date startDate )
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate( Date endDate )
    {
        this.endDate = endDate;
    }

    public int getMarkup()
    {
        return markup;
    }

    public void setMarkup( int markup )
    {
        this.markup = markup;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public Hotel getHotel()
    {
        return hotel;
    }

    public void setHotel( Hotel hotel )
    {
        this.hotel = hotel;
    }

    public List<RoomTypeDto> getRoomTypeDtos()
    {
        return roomTypeDtos;
    }

    public void setRoomTypeDtos( List<RoomTypeDto> roomTypeDtos )
    {
        this.roomTypeDtos = roomTypeDtos;
    }

    public double getRoomTotalPrice()
    {
        return roomTotalPrice;
    }

    public void setRoomTotalPrice( double roomTotalPrice )
    {
        this.roomTotalPrice = roomTotalPrice;
    }
}

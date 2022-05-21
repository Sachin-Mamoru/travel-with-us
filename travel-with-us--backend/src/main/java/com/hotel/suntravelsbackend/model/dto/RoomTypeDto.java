package com.hotel.suntravelsbackend.model.dto;

public class RoomTypeDto
{
    private long roomTypeId;
    private String typeName;
    private double price;
    private int maxAdults;
    private int noOfRooms;
    private double roomPrice;
    private int roomAdult;
    private int roomNo;

    public RoomTypeDto( long roomTypeId, String typeName, double price, int maxAdults, int noOfRooms, double roomPrice, int roomAdult, int roomNo )
    {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.price = price;
        this.maxAdults = maxAdults;
        this.noOfRooms = noOfRooms;
        this.roomPrice = roomPrice;
        this.roomAdult = roomAdult;
        this.roomNo = roomNo;
    }

    public long getRoomTypeId()
    {
        return roomTypeId;
    }

    public void setRoomTypeId( long roomTypeId )
    {
        this.roomTypeId = roomTypeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName( String typeName )
    {
        this.typeName = typeName;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice( double price )
    {
        this.price = price;
    }

    public int getMaxAdults()
    {
        return maxAdults;
    }

    public void setMaxAdults( int maxAdults )
    {
        this.maxAdults = maxAdults;
    }

    public int getNoOfRooms()
    {
        return noOfRooms;
    }

    public void setNoOfRooms( int noOfRooms )
    {
        this.noOfRooms = noOfRooms;
    }

    public double getRoomPrice()
    {
        return roomPrice;
    }

    public void setRoomPrice( double roomPrice )
    {
        this.roomPrice = roomPrice;
    }

    public int getRoomAdult()
    {
        return roomAdult;
    }

    public void setRoomAdult( int roomAdult )
    {
        this.roomAdult = roomAdult;
    }

    public int getRoomNo()
    {
        return roomNo;
    }

    public void setRoomNo( int roomNo )
    {
        this.roomNo = roomNo;
    }
}

package com.hotel.suntravelsbackend.model.dao;

public class RoomAdultCount
{
    private int noOfRooms;
    private int noOfAdults;

    public RoomAdultCount( int noOfRooms, int noOfAdults )
    {
        this.noOfRooms = noOfRooms;
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfRooms()
    {
        return noOfRooms;
    }

    public void setNoOfRooms( int noOfRooms )
    {
        this.noOfRooms = noOfRooms;
    }

    public int getNoOfAdults()
    {
        return noOfAdults;
    }

    public void setNoOfAdults( int noOfAdults )
    {
        this.noOfAdults = noOfAdults;
    }
}

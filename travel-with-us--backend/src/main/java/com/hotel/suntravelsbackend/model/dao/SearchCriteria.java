package com.hotel.suntravelsbackend.model.dao;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class SearchCriteria
{
    @Temporal( TemporalType.TIMESTAMP)
    private Date checkInDate;
    private int noOfNights;
    private List<RoomAdultCount> roomAdultCount;

    public SearchCriteria( Date checkInDate, int noOfNights, List<RoomAdultCount> roomAdultCount )
    {
        this.checkInDate = checkInDate;
        this.noOfNights = noOfNights;
        this.roomAdultCount = roomAdultCount;
    }

    public Date getCheckInDate()
    {
        return checkInDate;
    }

    public void setCheckInDate( Date checkInDate )
    {
        this.checkInDate = checkInDate;
    }

    public int getNoOfNights()
    {
        return noOfNights;
    }

    public void setNoOfNights( int noOfNights )
    {
        this.noOfNights = noOfNights;
    }

    public List<RoomAdultCount> getRoomAdultCount()
    {
        return roomAdultCount;
    }

    public void setRoomAdultCount( List<RoomAdultCount> roomAdultCount )
    {
        this.roomAdultCount = roomAdultCount;
    }
}

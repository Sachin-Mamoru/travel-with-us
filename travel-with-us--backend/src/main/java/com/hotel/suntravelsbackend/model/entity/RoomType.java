package com.hotel.suntravelsbackend.model.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "ROOM_TYPE_TBL" )
public class RoomType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomTypeId;

    @NotEmpty(message = "Type must have a name")
    private String typeName;

    @NotNull(message = "Price in mandatory")
    private double price;

    @NotNull(message = "Max capacity is mandatory")
    private int maxAdults;

    @NotNull(message = "Number of rooms is mandatory")
    private int noOfRooms;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contractId")
    @JsonIgnore
    private Contract contract;

    public RoomType( @NotEmpty( message = "Type must have a name" ) String typeName, @NotNull( message = "Price in mandatory" ) double price, @NotNull( message = "Max capacity is mandatory" ) int maxAdults, @NotNull( message = "Number of rooms is mandatory" ) int noOfRooms, Contract contract )
    {
        this.typeName = typeName;
        this.price = price;
        this.maxAdults = maxAdults;
        this.noOfRooms = noOfRooms;
        this.contract = contract;
    }

    @Override
    public String toString()
    {
        return "RoomType{" +
                       "roomTypeId=" + roomTypeId +
                       ", typeName='" + typeName + '\'' +
                       ", price=" + price +
                       ", maxAdults=" + maxAdults +
                       ", noOfRooms=" + noOfRooms +
                       '}';
    }
}

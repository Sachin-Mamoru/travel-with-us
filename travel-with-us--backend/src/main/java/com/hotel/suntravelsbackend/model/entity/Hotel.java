package com.hotel.suntravelsbackend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "HOTEL_TBL" )
public class Hotel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hotelId;

    @NotEmpty(message = "Name is mandatory")
    private String hotelName;

    @NotEmpty(message = "Address is mandatory")
    private String address;

    @NotEmpty(message = "Contact is mandatory")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="contact_no")
    private List<PhoneNo> phone;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email is mandatory")
    private String email;

//    @OneToMany(targetEntity = Contract.class, mappedBy = "hotel", cascade = CascadeType.ALL)
//    private List<Contract> contracts;


    public Hotel( @NotEmpty( message = "Name is mandatory" ) String hotelName, @NotEmpty( message = "Address is mandatory" ) String address, @NotEmpty( message = "Contact is mandatory" ) List<PhoneNo> phone, @NotEmpty( message = "Email is mandatory" ) @Email( message = "Email is mandatory" ) String email )
    {
        this.hotelName = hotelName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}

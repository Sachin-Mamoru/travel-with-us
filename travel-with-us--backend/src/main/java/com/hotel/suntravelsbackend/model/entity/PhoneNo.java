package com.hotel.suntravelsbackend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PhoneNo
{
    private String no;

    public PhoneNo( String no )
    {
        this.no = no;
    }
}

package com.hotel.suntravelsbackend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "CONTRACT_TBL" )
public class Contract
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contractId;

    @NotEmpty(message = "Contract Name is mandatory")
    private String name;

    @NotNull(message = "Contract Start Date is mandatory")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @NotNull(message = "Contract End Date is mandatory")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @NotNull(message = "Markup is mandatory")
    private int markup;

    @NotEmpty(message = "Description is mandatory")
    private String description;

    @NotEmpty(message = "Type is mandatory")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<RoomType> roomTypes;

    public Contract(String name, Date startDate, Date endDate, int markup, String description, String type, Hotel hotel, List<RoomType> roomTypes)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.markup = markup;
        this.description = description;
        this.type = type;
        this.hotel = hotel;
        this.roomTypes = roomTypes;
    }


}

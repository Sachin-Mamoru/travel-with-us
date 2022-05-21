package com.hotel.suntravelsbackend.repository;

import com.hotel.suntravelsbackend.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findAllByHotelNameIgnoreCaseContaining( String name);
}

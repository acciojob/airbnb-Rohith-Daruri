package com.driver.Service;

import com.driver.Repository.HotelManagementRepository;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementService {

    HotelManagementRepository hotelManagementRepository = new HotelManagementRepository();

    public String addHotel(Hotel hotel) {

        return hotelManagementRepository.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return hotelManagementRepository.addUser(user);
    }

    public String gethotelfacility() {
        return hotelManagementRepository.gethotelfacility();
    }

    public Integer bookroom(Booking booking) {
        return hotelManagementRepository.bookroom(booking);
    }

    public int getbookings(Integer aadharCard) {
        return hotelManagementRepository.getbookings(aadharCard);
    }

    public Hotel update(List<Facility> newFacilities, String hotelName) {
        return hotelManagementRepository.update(newFacilities,hotelName);
    }
}

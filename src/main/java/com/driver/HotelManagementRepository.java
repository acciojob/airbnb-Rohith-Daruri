package com.driver;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

@Repository
public class HotelManagementRepository {
    TreeMap<String, Hotel> hotelHashMap = new TreeMap<>();
    HashMap<String, User> userHashMap = new HashMap<>();
    HashMap<String,Booking>bookingHashMap = new HashMap<>();

    public String addHotel(Hotel hotel) {
        if(hotel==null || hotel.getHotelName()==null || hotelHashMap.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        hotelHashMap.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }

    public Integer addUser(User user) {
        userHashMap.put(user.getName(),user);
        return user.getaadharCardNo();
    }

    public String gethotelfacility() {
        int facility = 0;
        String result = "";
      for(String name:hotelHashMap.keySet()){
          Hotel h = hotelHashMap.get(name);
          if(h.getFacilities().size()>facility){
              result = h.getHotelName();
              facility = h.getFacilities().size();
          }

      }
      if(facility==0){
          return "";
      }
      return result;
    }

    public Integer bookroom(Booking booking) {
        booking.setBookingId(String.valueOf(UUID.randomUUID()));
        int rooms = booking.getNoOfRooms();
        String hotel = booking.getHotelName();
        if(hotelHashMap.containsKey(hotel)){
            int available_rooms = hotelHashMap.get(hotel).getAvailableRooms();
            if(rooms==available_rooms){
                int value = booking.getNoOfRooms()*hotelHashMap.get(hotel).getPricePerNight();
                booking.setAmountToBePaid(value);
                bookingHashMap.put(booking.getBookingId(),booking);
                return value;
            }
        }
        return -1;
    }

    public int getbookings(Integer aadharCard) {
        int Count =0;
        for(String str:bookingHashMap.keySet()){
            Booking b = bookingHashMap.get(str);
            if(aadharCard==b.getBookingAadharCard()){
                Count++;
            }
        }
        return Count;
    }

    public Hotel update(List<Facility> newFacilities, String hotelName) {
        List<Facility> a = hotelHashMap.get(hotelName).getFacilities();
        for(Facility f:newFacilities){
            if(a.contains(f)==false){
                a.add(f);
            }
        }
        Hotel hotel = hotelHashMap.get(hotelName);
        hotel.setFacilities(a);
        hotelHashMap.put(hotelName,hotel);
        return hotel;
    }
}
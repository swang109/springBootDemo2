package com.example.demo.service;

import com.example.demo.exception.StayDeleteException;
import com.example.demo.model.*;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.StayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StayService {
    private StayRepository stayRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public StayService(StayRepository stayRepository, ReservationRepository reservationRepository) {
        this.stayRepository = stayRepository;
        this.reservationRepository = reservationRepository;

    }

    public List<Stay> listByUser(String username) {
        return stayRepository.findByHost(new Customer.Builder().setUsername(username).build());
    }


    public Stay findByIdAndHost(Long stayId) {
        return stayRepository.findById(stayId).orElse(null);
    }

    public void add(Stay stay) {
        LocalDate date = LocalDate.now().plusDays(1);
        List<StayAvailability> availabilities = new ArrayList<>();
        for (int i = 0; i < 30; ++i) {
            availabilities.add(new StayAvailability.Builder().setId(new StayAvailabilityKey(stay.getId(), date)).setStay(stay).setState(StayAvailabilityState.AVAILABLE).build());
            date = date.plusDays(1);
        }
        stay.setAvailabilities(availabilities);
        stayRepository.save(stay);
    }

    public void delete(Long stayId) {

        List<Reservation> reservations = reservationRepository.findByStayAndCheckoutDateAfter(new Stay.Builder().setId(stayId).build(), LocalDate.now());
        if (reservations != null && reservations.size() > 0) {
            throw new StayDeleteException("Cannot delete stay with active reservation");
        }

        stayRepository.deleteById(stayId);
    }

}

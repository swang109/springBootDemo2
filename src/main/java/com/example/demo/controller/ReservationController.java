package com.example.demo.controller;

import com.example.demo.exception.InvalidReservationDateException;
import com.example.demo.model.Customer;
import com.example.demo.model.Reservation;
import com.example.demo.model.Stay;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/reservations")
    public List<Reservation> listReservations(@RequestParam String username) {
        System.out.println("shengyang "+username);
        return reservationService.listByGuest(username);
    }

    @PostMapping("/reservations")
    public void addReservation(@RequestBody Reservation reservation) {
        System.out.println("shengyang  :  "+reservation.getGuest().getUsername());
        LocalDate checkinDate = reservation.getCheckinDate();
        LocalDate checkoutDate = reservation.getCheckoutDate();
        if (checkinDate.equals(checkoutDate) || checkinDate.isAfter(checkoutDate) || checkinDate.isBefore(LocalDate.now())) {
            throw new InvalidReservationDateException("Invalid date for reservation");
        }
        //reservation.setGuest(new Customer.Builder().setUsername(userName).build());
        reservationService.add(reservation);
    }

    @DeleteMapping("/reservations/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId) {
        reservationService.delete(reservationId);
    }
}
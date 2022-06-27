package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.model.Stay;
import com.example.demo.service.ReservationService;
import com.example.demo.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class StayController {
    private StayService stayService;
    private ReservationService reservationService;

    @Autowired
    public StayController(StayService stayService, ReservationService reservationService) {
        this.stayService = stayService;
        this.reservationService = reservationService;
    }
    @GetMapping(value = "/stays")
    public List<Stay> listStays(@RequestParam(name = "host") String hostName) {
        return stayService.listByUser(hostName);
    }

    @GetMapping(value = "/stays/{stayId}")
    public Stay getStay(@PathVariable Long stayId) {
        return stayService.findByIdAndHost(stayId);
    }

    @PostMapping("/stays")
    public void addStay(@RequestBody Stay stay) {
        stayService.add(stay);
    }

    @DeleteMapping("/stays/{stayId}")
    public void deleteStay(@PathVariable Long stayId) {
        stayService.delete(stayId);
    }

    @GetMapping(value = "/stays/reservations/{stayId}")
    public List<Reservation> listReservations(@PathVariable Long stayId, Principal principal) {
        return reservationService.listByStay(stayId);
    }

}

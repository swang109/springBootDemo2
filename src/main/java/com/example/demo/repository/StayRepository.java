package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
    //common methods like save, deleteById, findById are defined in the JpaRepository
    List<Stay> findByHost(Customer customer);

}

package com.example.demo.service;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.model.Authority;
import com.example.demo.model.Customer;
import com.example.demo.model.UserRole;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterService {
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    public RegisterService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    //Transactional make the insert operations to user and authority tables will succeed together, or fail together.
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(Customer customer, UserRole role) {
        if (userRepository.existsById(customer.getUsername())) {
            throw new UserAlreadyExistException("User already exists");
        }
        customer.setPassword(customer.getPassword());
        customer.setEnabled(true);

        userRepository.save(customer);
        authorityRepository.save(new Authority(customer.getUsername(), role.name()));
    }

    public String retrieve(String username) {
        return userRepository.findById(username).get().getUsername();
    }

    public List<Customer> retrieveAll() {
        return userRepository.findAll();
    }
}

package com.revature.services;

import com.revature.models.user.*;

public class UserService {

    public Customer createCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        return customer;
    }

}

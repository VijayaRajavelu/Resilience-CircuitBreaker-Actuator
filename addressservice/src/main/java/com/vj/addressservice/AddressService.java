package com.vj.addressservice;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddressService {

    private Map<Long, Address> addressDatabase = new HashMap<>();

    @PostConstruct
    public void populatedData(){
        Address a1 = new Address(1L, "First Street", "Chennai", "India");
        Address a2 = new Address(2L, "Second Street", "Delhi", "India");
        Address a3 = new Address(3L, "Third Street", "Mumbai", "India");
        Address a4 = new Address(4L, "Fourth Street", "Bengaluru", "India");

        addressDatabase.put(1L, a1);
        addressDatabase.put(2L, a2);
        addressDatabase.put(3L, a3);
        addressDatabase.put(4L, a4);
    }


    public Address getAddressData(Long empId){
        return addressDatabase.get(empId);
    }

}

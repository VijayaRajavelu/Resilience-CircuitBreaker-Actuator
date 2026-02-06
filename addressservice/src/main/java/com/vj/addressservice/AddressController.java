package com.vj.addressservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/add")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @GetMapping("/address/{empId}")
    public ResponseEntity<Address> getAddressData(@PathVariable Long empId){

        Address response = addressService.getAddressData(empId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.vj.employeeservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "addressservice")
public interface AddressService {

    @GetMapping("/add/address/{empId}")
    ResponseEntity<Address> getAddressData(@PathVariable("empId") Long empId);

}
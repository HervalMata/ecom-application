package com.herval.ecomapp.dto;

import lombok.Data;

@Data
public class AddressDTO {
    public String street;
    public String city;
    public String state;
    public String country;
    public String zipCode;
}

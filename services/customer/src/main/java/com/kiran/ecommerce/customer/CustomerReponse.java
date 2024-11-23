package com.kiran.ecommerce.customer;

public record CustomerReponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {

}

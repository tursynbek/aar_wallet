package com.example.tour_agency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member
{
    private String uname, email, pass;
    private int phone;


    public Member(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
}

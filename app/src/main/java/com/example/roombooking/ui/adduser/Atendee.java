package com.example.roombooking.ui.adduser;

/**
 * Created by Vinod on 11/18/2017.
 */

public class Atendee {

    private String name;
    private String email;
    private String number;

    public Atendee(){

    }
    public Atendee(String name,String email,String phone){
        this.name=name;
        this.email=email;
        this.number=phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}

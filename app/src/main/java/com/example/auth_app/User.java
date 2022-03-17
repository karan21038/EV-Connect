package com.example.auth_app;

public class User {
    public String u_name,u_age,u_email;
    public String ev_type;
    public String car_no;

    public User(){}

    public User(String u_name, String u_age, String u_email,String ev_type, String car_no) {
        this.u_name = u_name;
        this.u_age = u_age;
        this.u_email = u_email;
        this.ev_type = ev_type;
        this.car_no = car_no;
    }
}

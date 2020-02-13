package com.sally.HealthApp.service;

import com.sally.HealthApp.data.model.LoginDetails;

public interface LoginService {

    String EVENT = "EVENT";
    String USER = "USER";

    int createAUser(LoginDetails LoginDetails);

    int login(LoginDetails loginDetails);
}

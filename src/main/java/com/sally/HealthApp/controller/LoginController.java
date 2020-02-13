package com.sally.HealthApp.controller;


import com.sally.HealthApp.data.model.LoginDetails;
import com.sally.HealthApp.data.model.LoginResource;
import com.sally.HealthApp.data.model.ResponseModel;
import com.sally.HealthApp.service.LoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "LoginControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    @Autowired
    private LoginService loginService;


    // username,email,password
    // http://localhost:8080/login/createLogin?email=test&username=test&password=test
    @RequestMapping(value = "/createLogin", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> createLogin (@RequestParam(value = "email")String email,
                                          @RequestParam(value = "username")String username,
                                          @RequestParam(value = "password")String password){
        ResponseModel respMod;
        if(email == null || email.isEmpty()){
            respMod = new ResponseModel(false,-1,"No email");
            return new ResponseEntity<>(respMod, HttpStatus.BAD_REQUEST);
        }
        if(username == null || username.isEmpty()){
            respMod = new ResponseModel(false,-1,"No Username");
            return new ResponseEntity<>(respMod, HttpStatus.BAD_REQUEST);
        }
        if(password == null || password.isEmpty()){
            respMod = new ResponseModel(false,-1,"No Password");
            return new ResponseEntity<>(respMod, HttpStatus.BAD_REQUEST);
        }
        // type of data model
        LoginResource loginResource = new LoginResource(username,email,password);

        //where will call the interface/service layer to continue the creation of the account
        int responseCode = loginService.createAUser(toLoginDetail(loginResource));
        if(responseCode == 0){
            respMod = new ResponseModel(true, 0, Response(responseCode));
            return new ResponseEntity<>(respMod, HttpStatus.ACCEPTED);
        }else {
            respMod = new ResponseModel(false, responseCode, Response(responseCode));
            return new ResponseEntity<>(respMod, HttpStatus.CONFLICT);
        }
    }
    //login/login
    @RequestMapping(value = "login",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> login(@RequestParam(value ="email")String email,
                                 @RequestParam(value = "password")String password){
        LoginResource login = new LoginResource();
        ResponseModel responseModel;
        if(email.isEmpty() || email == null){
            responseModel = new ResponseModel(false,-1,"Username us non existent");
            return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
        }if(password.isEmpty() || password == null){
            responseModel = new ResponseModel(false,-1,"password is non existent");
            return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
        }
        login.setEmail(email);
        login.setPassword(password);

        int response = loginService.login(toLoginDetail(login));

        if(response == 0){
            responseModel = new ResponseModel(true,0,Response(response));
            return new ResponseEntity<>(responseModel,HttpStatus.ACCEPTED);
        }else{
            responseModel = new ResponseModel(false,response,Response(response));
            return new ResponseEntity<>(responseModel,HttpStatus.ACCEPTED);
        }

    }

    private static LoginDetails toLoginDetail(LoginResource loginResource) {
        LoginDetails loginDetails = new LoginDetails();

        loginDetails.setEmail(loginResource.getEmail());
        loginDetails.setUsername(loginResource.getUsername());
        loginDetails.setPassword(loginResource.getPassword());

        return loginDetails;
    }

    /**
     * based on the response code "x" you get a error message
     *
     * @param x this is the error code sent in by the service layer
     * @return String Error message
     */
    private String Response(int x) {
        switch (x) {
            case 0:
                return "Valid entry";
            case 1:
                return "Issue with name field";
            case 2:
                return "Issue with info";
            case 3:
                return "Issue with image loc. field, missing \".\" ";
            case 4:
                return "Issue with weblink field, missing \".com\"";
            case 7:
                return "Could not login";
            case 10:
                return "Issue with Owner Spec.";
        }
        return "null";
    }

}

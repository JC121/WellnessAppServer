package com.sally.HealthApp.service;

import com.sally.HealthApp.data.model.LoginDetails;
import com.sally.HealthApp.data.model.LoginDocument;
import com.sally.HealthApp.data.repo.LoginModelRepo;
import com.sally.HealthApp.data.repo.LoginModelRepoImpl;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private LoginModelRepoImpl loginModelRepoImpl;

    private LoginModelRepo loginModelRepo;

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * This validates the data the obj model has and then passes it to the Repo. layer
     *
     * @param loginDetails Object model to be added
     * @return response code
     */
    @Override
    public int createAUser(LoginDetails loginDetails) {
        //Check if the person/account already exists
        if(!isValidChar(loginDetails.getEmail())){
            return 1;
        }
        if(!isValidChar(loginDetails.getPassword())){
            return 2;
        }
        if(!isValidChar(loginDetails.getUsername())){
            return 3;
        }
        loginModelRepo.addUser(toLoginDocument(loginDetails));
        return 0;
    }

    @Override
    public int login(LoginDetails loginDetails){
        if(loginModelRepoImpl.isUser(toLoginDocument(loginDetails))){
            if(loginModelRepoImpl.validPass(toLoginDocument(loginDetails))){
                return 0;
            }
        }
        return 7;
    }

    private boolean isValidChar(CharSequence seq) {
        int len = seq.length();
        for (int i = 0; i < len; i++) {
            char c = seq.charAt(i);

            // Test for all positive cases
            if ('0' <= c && c <= '9') continue;
            if ('a' <= c && c <= 'z') continue;
            if ('A' <= c && c <= 'Z') continue;
            if (c == ' ') continue;
            if (c == '&') continue;
            if (c == '-') continue;
            if (c == '.') continue;
            if (c == ',') continue;
            if (c == '\'') continue;
            if (c == '(') continue;
            if (c == ')') continue;
            if (c == '+') continue;
            if (c == ':') continue;
            if (c == '$') continue;
            if (c == '/') continue;
            if (c == '!') continue;


            // ... insert more positive character tests here
            // If we get here, we had an invalid char, fail right away
            //System.out.println(c);
            return false;
        }
        // All seen chars were valid, succeed
        return true;
    }

    private static LoginDocument toLoginDocument(LoginDetails loginDetails) {
        LoginDocument loginDocument = new LoginDocument();

        loginDocument.setEmail(loginDetails.getEmail());
        loginDocument.setUsername(loginDetails.getUsername());
        loginDocument.setPassword(loginDetails.getPassword());

        return loginDocument;
    }

}

package com.sally.HealthApp.data.repo;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.sally.HealthApp.data.model.LoginDocument;
import com.sally.HealthApp.manager.JsonToObject;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class LoginModelRepoImpl implements LoginModelCustomRepo {
    private JsonToObject JTO = new JsonToObject();
    @Autowired
    private LoginModelRepo loginModelRepo;
    @Autowired
    private Bucket bucket;

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();


    /**
     * ID Generated and set to POJO
     * If it exists then Error occurs
     *
     * @param loginDocument the document that is to be added
     */
    @Override
    public void addUser(LoginDocument loginDocument) {
        String id = loginDocument.getUsername();
        loginDocument.setID(id);
        if (!this.contains(id)) {
            loginModelRepo.save(loginDocument);
        } else {
            throw new IllegalArgumentException("ID error");
        }
    }


    public boolean contains(String id) {
        JsonDocument doc = JsonDocument.create(id, JsonObject.create().put("temp", "temp"));
        return bucket.exists(doc);
    }

    public boolean isUser(LoginDocument loginDocument){
        return !loginModelRepo.findByEmail(loginDocument.getEmail()).isEmpty();
    }

    public boolean validPass(LoginDocument loginDocument){
        if (isUser(loginDocument)){
            ArrayList<LoginDocument> list = new ArrayList<>(loginModelRepo.findByEmail(loginDocument.getEmail()));
            System.out.println("UsersFound: "+list.get(0).toString());
            System.out.println(1);
            LoginDocument tempLogin = list.get(0);
            System.out.println(2);
            if(tempLogin.getPassword().equals((loginDocument.getPassword()))) {
                System.out.println(3);
                return true;
            }
        }
        return false;
    }


}

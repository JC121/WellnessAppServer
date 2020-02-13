package com.sally.HealthApp.data.model;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.java.repository.annotation.Field;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;

@NoArgsConstructor
@Data
@Document
public class LoginResource {
    /**
     * This object model is specific to the Controller layer
     */
    @Field
    @JsonProperty("username")
    String username;

    @Field
    @JsonProperty("email")
    String email;

    @Field
    @JsonProperty("password")
    String password;

    public LoginResource(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }
}

package com.sally.HealthApp.data.model;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;

@NoArgsConstructor
@Data
@Document
public class LoginDocument {
    /**
     * This object model is specific to the Repository layer
     */
    @Id
    @JsonProperty("id")
    String ID;

    @Field
    @JsonProperty("username")
    String username;

    @Field
    @JsonProperty("email")
    String email;

    @Field
    @JsonProperty("password")
    String password;

    public LoginDocument(String ID, String username, String email, String password) {
        this.ID = ID;
        this.username = username;
        this.email = email;
        this.password = password;

    }
}

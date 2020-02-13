package com.sally.HealthApp.data.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JacksonArrayExample {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        //"["Diabetes Quiz"]"
        //String json = "[\"name\"]";
        String json = "[\"Diabetes Quiz\"]";
        System.out.println(json);
        try {

            // 1. convert JSON array to Array objects
            String[] pp1 = mapper.readValue(json, String[].class);

            System.out.println("JSON array to Array objects...");
            for (String person : pp1) {
                System.out.println(person);
            }

            // 2. convert JSON array to List of objects
            List<String> ppl2 = Arrays.asList(mapper.readValue(json, String[].class));

            System.out.println("\nJSON array to List of objects");
            ppl2.stream().forEach(x -> System.out.println(x));

            // 3. alternative
            List<String> pp3 = mapper.readValue(json, new TypeReference<List<String>>() {});

            System.out.println("\nAlternative...");
            pp3.stream().forEach(x -> System.out.println(x));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
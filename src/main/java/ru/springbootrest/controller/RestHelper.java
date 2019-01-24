package ru.springbootrest.controller;

import org.springframework.web.client.RestTemplate;
import ru.springbootrest.model.User;

public class RestHelper {
    RestTemplate restTemplate = new RestTemplate();
    static final String URL_USERS = "http://localhost:8080/rest/user";

    protected User[] forGetAll() {
        return null;
    }

}

package ru.springbootrest.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.springbootrest.model.Role;
import ru.springbootrest.model.User;

import java.util.HashSet;
import java.util.Set;

public class RestHelper {
    RestTemplate restTemplate = new RestTemplate();
    static final String URL_USERS = "http://localhost:8080/rest/user";

    protected User[] forGetAll() {
        User[] usersArray = restTemplate.getForObject(URL_USERS + "/all", User[].class);
        return usersArray;
    }

    protected User forAddUserPost (String name, String login, String password, String roleName) {
        Long roleId = roleName.equals("Admin") ? 1L : 2L;
        Role role = new Role(roleName);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role); role.setId(roleId);
        User newUser = new User(name, login, password, roleSet);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);
        User userDone = restTemplate.postForObject(URL_USERS + "/add", requestBody, User.class);
        System.out.println("added user json string = " + new Gson().toJson(userDone) );
        return userDone;
    }

    protected User forEditUserPost (Long id, String name, String login, String password, String roleName) {
        Long roleId = roleName.equals("Admin") ? 1L : 2L;
        Role role = new Role(roleName);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        role.setId(roleId);
        User editUser = new User(id, name, login, password, roleSet);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Data attached to the request.
        HttpEntity<User> requestBody = new HttpEntity<>(editUser, headers);
        User userUpdated = restTemplate.exchange(URL_USERS + "/edit", HttpMethod.PUT, requestBody, User.class).getBody();
        //restTemplate.put(URL_USERS + "/edit", requestBody, Void.class);
        System.out.println("updated user json string = " + new Gson().toJson(userUpdated));
        return userUpdated;
    }

    protected void forDelUserGet(Long id) {
        restTemplate.delete(URL_USERS + "/del/" + id);
        System.out.println("Deleted user id = " + id);
    }


}


// String jsonNewUserString = "{\"name\":\"" + name + "\",\"login\":\"" + login + "\",\"password\":\"" + password + "\",\"roles\":[{\"id\":" + roleId + ",\"roleName\":\"" + roleName + "\"}]}";
//	String sss = "{\"name\":\"66qa\",\"login\":\"66qa\",\"password\":\"66qa\",\"roles\":[{\"id\":2,\"roleName\":\"User\"}]}";

// String jsonEditUserString = "{\"id\":" + id + ",\"name\":\"" + name + "\",\"login\":\"" + login + "\",\"password\":\"" + password + "\",\"roles\":[{\"id\":" + roleId + ",\"roleName\":\"" + roleName + "\"}]}";
// {"id":118,"name":"kp","login":"kp","password":"kp","roles":[{"id":1,"roleName":"Admin","users":[]}]}
package ru.springbootrest.controller;



import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.springbootrest.model.Role;
import ru.springbootrest.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
	private RestHelper restHelper = new RestHelper();

	@RequestMapping(value = "/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/adminrest")
	public String adminPageREST(){
		return "adminrest";
	}


	@RequestMapping(value = "/userrest", method = RequestMethod.GET)
	public String userPageREST(Model modelMap) {
		User[] usersArray = restHelper.forGetAll();
		modelMap.addAttribute("usersArray", usersArray);
		return "userrest";
	}

	@RequestMapping(value = "/userrest/add", method = RequestMethod.POST)
	public String addUserPage(@RequestParam(value = "name") String name,
							  @RequestParam(value = "login") String login,
							  @RequestParam(value = "password") String password,
							  @RequestParam(value = "role") String roleName) {
		User userDone = restHelper.forAddUserPost(name, login, password, roleName);
		return "redirect:/userrest";
	}

	@RequestMapping(value = { "/userrest/edit" }, method = RequestMethod.POST)
	public String updateUser(@RequestParam(value = "id") Long id,
							 @RequestParam(value = "login") String login,
							 @RequestParam(value = "name") String name,
							 @RequestParam(value = "password") String password,
							 @RequestParam(value = "role") String roleName) {
		User userUpdated = restHelper.forEditUserPost(id, name, login, password, roleName);
		return "redirect:/userrest";
	}

	@RequestMapping(value = "/userrest/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id) {
		restHelper.forDelUserGet(id);
		return "redirect:/userrest";
	}

	@RequestMapping(value = "/access_denied")
	public String deniesPage() {
		return "access_denied";
	}






}
package ru.springbootrest.controller;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	RestTemplate restTemplate = new RestTemplate();
	static final String URL_USERS = "http://localhost:8080/rest/user";

/*	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public String getWelcome(ModelMap modelMap) {
		modelMap.addAttribute("adminRole", new Role("Admin"));
		modelMap.addAttribute("userRole", new Role("User"));
		modelMap.addAttribute("adminName", getPrincipal());
		modelMap.addAttribute("users", userService.getAllUsers());
		return "welcome";
	} */

	@RequestMapping(value = "/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/adminrest")
	public String adminPageREST(){
		return "adminrest";
	}

	/*
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap modelMap) throws Exception {
		modelMap.addAttribute("adminRole", new Role("Admin"));
		modelMap.addAttribute("userRole", new Role("User"));
		modelMap.addAttribute("adminName", getPrincipal());
		modelMap.addAttribute("users", userService.getAllUsers());
		return "admin";
	}
	 */

	@RequestMapping(value = "/userrest", method = RequestMethod.GET)
	public String userPageREST(Model modelMap) {
		String jsonUsersString = restTemplate.getForObject(URL_USERS + "/all", String.class);
		modelMap.addAttribute("jsonusers", jsonUsersString);
		return "userrest";
	}


	@RequestMapping(value = "/userrest/add", method = RequestMethod.POST)
	public String addUserPage(@RequestParam(value = "name") String name,
							  @RequestParam(value = "login") String login,
							  @RequestParam(value = "password") String password,
							  @RequestParam(value = "role") String roleName) {
		//       System.out.println("name = " +  name + " login = " + login + " password = " + password + " rolename = " + roleName);
		int roleId = roleName.equals("Admin") ? 1 : 2;
		String jsonNewUserString = "{\"name\":\"" + name + "\",\"login\":\"" + login + "\",\"password\":\"" + password + "\",\"roles\":[{\"id\":" + roleId + ",\"roleName\":\"" + roleName + "\"}]}";
		//	String sss = "{\"name\":\"66qa\",\"login\":\"66qa\",\"password\":\"66qa\",\"roles\":[{\"id\":2,\"roleName\":\"User\"}]}";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Data attached to the request.
		HttpEntity<String> requestBody = new HttpEntity<>(jsonNewUserString, headers);
		String e = restTemplate.postForObject(URL_USERS + "/add", requestBody, String.class);

//String addedUserString = restTemplate.
		return "redirect:/userrest";
	}


	@RequestMapping(value = { "/admin/edit" }, method = RequestMethod.POST)
	public String updateUser(@RequestParam(value = "id") String id,
							 @RequestParam(value = "login") String login,
							 @RequestParam(value = "name") String name,
							 @RequestParam(value = "password") String password,
							 @RequestParam(value = "role") String roleName) {

		return "redirect:/admin";
	}




	@RequestMapping(value = "/access_denied")
	public String deniesPage() {
		return "access_denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
	/*	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		} */
		return "redirect:/login?logout";
	}




}
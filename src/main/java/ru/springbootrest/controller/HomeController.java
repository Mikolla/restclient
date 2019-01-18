package ru.springbootrest.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
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
		System.out.println(jsonUsersString);
		modelMap.addAttribute("jsonusers", jsonUsersString);

		return "userrest";
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

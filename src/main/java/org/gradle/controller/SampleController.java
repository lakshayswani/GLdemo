package org.gradle.controller;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.gradle.pojo.User;

@Controller
public class SampleController {
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private ArrayList<User> users =new ArrayList<>();
    
    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping(value="/adduser", params="action=Add")
    public String addMore(Model model, @ModelAttribute User user) {
    	user.setId((int)counter.incrementAndGet());
        users.add(user);
    	model.addAttribute("userList", users);
        model.addAttribute("user", new User());
        return "adduser";
    }
    @PostMapping(value="/adduser", params="action=View")
    @ResponseBody
    public ArrayList<User> json() {
    	return users;
    }
    
}
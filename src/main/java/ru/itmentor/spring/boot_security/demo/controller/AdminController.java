package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "create";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("authorities") List<String> values){
        user.setRoles(roleService.setRole(values));
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "update";
    }

    @RequestMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Long id,
                       @ModelAttribute("user") User user,
                       @RequestParam("authorities") List<String> values){
        user.setRoles(roleService.setRole(values));
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/check")
    public String checkUser(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", userService.getUserById(id));
        return "checkUser";
    }
}
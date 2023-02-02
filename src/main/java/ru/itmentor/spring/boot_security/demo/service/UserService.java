package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
    void updateUser(Long id, User user);
    User getUserByEmail(String email);
}
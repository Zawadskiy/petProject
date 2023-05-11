package com.example.petproject.service.user;


import com.example.petproject.model.User;

import java.util.List;

public interface UserService {
    User updateUser(User user);
    User create(User user);
    User findById(long id);
    void deleteById(long id);
    List<User> getUsers(int page, int size);
}

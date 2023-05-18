package com.example.petproject.service.user;


import com.example.petproject.model.User;

import java.util.List;

public interface UserService {
    User update(User user);
    User create(User user);
    User getUser(long id);
    void delete(long id);
    List<User> getUsers(int page, int size);
}

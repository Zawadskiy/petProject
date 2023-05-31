package com.example.petproject.service.user;


import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.domain.User;

import java.util.List;

public interface UserService {
    User update(User user);

    User create(User user);

    User create(SignupRequest signupRequest);

    User getUser(long id);

    void delete(long id);

    List<User> getUsers(int page, int size);
}

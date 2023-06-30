package com.example.petproject.service.user;


import com.example.petproject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User update(User user);

    User create(User user);

    User getUser(long id);

    void delete(long id);

    Page<User> getAll(Pageable pageable);

    String encode(CharSequence rawPassword);
}

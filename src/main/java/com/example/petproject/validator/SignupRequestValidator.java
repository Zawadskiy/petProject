package com.example.petproject.validator;

import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.exception.DuplicateUsernameException;
import com.example.petproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignupRequestValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public SignupRequestValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupRequest.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        SignupRequest request = (SignupRequest) target;

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUsernameException("Username is already taken!");
        }
    }
}

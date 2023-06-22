package com.example.petproject.validator;

import com.example.petproject.dto.request.SignupRequest;
import com.example.petproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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

        // TODO: 23.06.2023 почему не анноташками?
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password is required");

        // TODO: 23.06.2023 можно же прямо здесь получить скащенный объект, без явного приведения внутри ифа
        if (target instanceof SignupRequest) {

            SignupRequest request = (SignupRequest) target;

            if (userRepository.existsByUsername(request.getUsername())) {
                errors.rejectValue("username", "Username is already taken!");
            }
        }
    }
}

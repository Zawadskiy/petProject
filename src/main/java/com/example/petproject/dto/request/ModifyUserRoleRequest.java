package com.example.petproject.dto.request;

import com.example.petproject.model.ERole;
import jakarta.validation.constraints.NotBlank;

public class ModifyUserRoleRequest {

    @NotBlank
    private String username;

    //TODO Конвертит джисоны в классы не спринг а десериадизатор. В спринте это Джексон.
    // У Джексона есть политики как мапить енумы. По умолчанию это метод valueOf.
    // Но можно указать кастомный. @JsonCreator. Правда хз як це допоможе мені з exception)
    private ERole roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ERole getRoleName() {
        return roleName;
    }

    public void setRoleName(ERole roleName) {
        this.roleName = roleName;
    }
}

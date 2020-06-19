package com.challenge.service.dto;

import com.challenge.entity.Role;
import com.challenge.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;
    private String email;
    private String status;
    private String roleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.roleName = user.getRoles().stream().map(Role::getName).findAny().orElse(null);
        this.createdAt = user.getCreatedAt();
        this.status = StatusUserEnum.fromId(user.getStatus()).getDescription();
    }
}

package com.challenge.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserPutDto {
    @Email
    private String email;

    @NotEmpty
    @NotBlank
    @Size(max = 30)
    private String password;
}

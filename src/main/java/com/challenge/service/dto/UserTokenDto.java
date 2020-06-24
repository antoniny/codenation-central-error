package com.challenge.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenDto {

            private String access_token;
            private String token_type;
            private String refresh_token;
            private Long expires_in;
            private String scope;
}

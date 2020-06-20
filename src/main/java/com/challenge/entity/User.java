package com.challenge.entity;

import com.challenge.service.dto.UserPostDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    @NotBlank
    @JsonIgnore
    private String password;

    @NotEmpty
    @NotBlank
    @JsonIgnore
    @Size(max = 1)
    private String status;

    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    List<Role> roles;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreatedDate
    private LocalDateTime createdAt;

    public User(UserPostDto userPostDto) {
        setName(userPostDto.getName());
        setEmail(userPostDto.getEmail());
        setPassword(userPostDto.getPassword());
    }
}

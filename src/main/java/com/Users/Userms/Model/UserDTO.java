package com.Users.Userms.Model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Getter
@Setter
public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private String phone_number;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}

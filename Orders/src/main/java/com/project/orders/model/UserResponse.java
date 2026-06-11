package com.project.orders.model;

import lombok.Data;

@Data
public class UserResponse {

    private Long user_id;
    private String user_name;
    private String email_id;
    private String roles;
}

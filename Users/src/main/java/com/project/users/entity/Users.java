package com.project.users.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.id.IdentityGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String user_name;
    private String email_id;

}

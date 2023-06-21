package ru.legas.instazoo.entity;

import jakarta.persistence.PrePersist;
import ru.legas.instazoo.entity.enums.ERole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String middlename;
    private String email;
    private String bio;
    private String username;
    private String password;

    private Set<ERole> role = new HashSet<>();
    private List<Post> posts = new ArrayList<>();
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}

package com.example.dogadjaji213.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
<<<<<<< HEAD
 /*   @OneToMany(mappedBy = "role")
=======
    /*@OneToMany(mappedBy = "role")
>>>>>>> 4ad065b9f6ef31b4d95e9eabdd79cd039b41f7f0
    private List<AppUser> users;*/
}

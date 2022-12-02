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
 /*   @OneToMany(mappedBy = "role")
    private List<AppUser> users;*/
}

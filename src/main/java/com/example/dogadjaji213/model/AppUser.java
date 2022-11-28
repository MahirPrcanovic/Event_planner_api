package com.example.dogadjaji213.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    public AppUser(String firstName,String lastName,String email,String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}

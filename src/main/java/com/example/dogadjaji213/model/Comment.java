package com.example.dogadjaji213.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    public Comment(String comment){
        this.comment=comment;
    }
    @Id
    @GeneratedValue()
    private UUID id;
    private String comment;
    @ManyToOne
    private AppUser appUser;
    @ManyToOne
    private Event event;

}

package com.example.dogadjaji213.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public Comment(String comment,AppUser appUser,Event event){
        this.comment=comment; this.appUser=appUser;
        this.event=event;
    }
    @Id
    @GeneratedValue()
    private UUID id;
    private String comment;
    @ManyToOne
    private AppUser appUser;
    @ManyToOne
    @JsonIgnore
    private Event event;
}

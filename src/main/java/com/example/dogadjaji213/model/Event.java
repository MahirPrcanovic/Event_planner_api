package com.example.dogadjaji213.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    public Event(String name,LocalDateTime date,String description,String pictureUrl){
        this.name=name;
        this.date=date;
        this.description=description;
        this.pictureUrl=pictureUrl;
    }
    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
    private LocalDateTime date;
    private String description;
    private String pictureUrl;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Category category;
<<<<<<< HEAD
    /*@OneToMany(mappedBy = "event")
=======
/*    @OneToMany(mappedBy = "event")
>>>>>>> 4ad065b9f6ef31b4d95e9eabdd79cd039b41f7f0
    private List<Comment> comments;*/
}

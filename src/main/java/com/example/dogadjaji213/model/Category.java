package com.example.dogadjaji213.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Category {
    public Category(String name,String iconUrl){
        this.name=name;
        this.iconUrl=iconUrl;
    }
    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
    private String iconUrl;
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Event> events;
}

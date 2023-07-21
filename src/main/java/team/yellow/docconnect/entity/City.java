package team.yellow.docconnect.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "city")
@Data

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
}

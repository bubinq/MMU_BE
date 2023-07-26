package team.yellow.docconnect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String summary;
    @Column(nullable = false)
    private int experience;
//    @Column(nullable = false)
    private String education;
    @Column(name = "avg_rating", nullable = false)
    private double averageRating;
    @Column(nullable = false)
    private String imageUrl;
    @Column(name = "working_address", nullable = false)
    private String address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "main_specialty_id")
    private Specialty specialty;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;
}

package team.yellow.docconnect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "state")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    //FOR NEXT SPRINT WIP
//    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<City> cities = new HashSet<>();
}

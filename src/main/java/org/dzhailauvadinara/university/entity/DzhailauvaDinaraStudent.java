package org.dzhailauvadinara.university.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "students")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DzhailauvaDinaraStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @Email @NotBlank private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private DzhailauvaDinaraUser user;
}

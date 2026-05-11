package org.dzhailauvadinara.university.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "courses")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DzhailauvaDinaraCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String title;
    private String description;
    private Integer credits;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private DzhailauvaDinaraTeacher teacher;
}
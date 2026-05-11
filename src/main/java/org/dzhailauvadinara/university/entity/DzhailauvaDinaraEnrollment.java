package org.dzhailauvadinara.university.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DzhailauvaDinaraEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private DzhailauvaDinaraStudent student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private DzhailauvaDinaraCourse course;

    private LocalDateTime enrolledAt;
    private Double grade;
}

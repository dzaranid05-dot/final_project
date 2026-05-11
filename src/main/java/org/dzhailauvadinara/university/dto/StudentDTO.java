package org.dzhailauvadinara.university.dto;

import lombok.*;

@Data @Builder
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
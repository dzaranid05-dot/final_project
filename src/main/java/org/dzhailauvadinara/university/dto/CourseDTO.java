package org.dzhailauvadinara.university.dto;

import lombok.*;

@Data @Builder
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Integer credits;
    private String teacherName;
}

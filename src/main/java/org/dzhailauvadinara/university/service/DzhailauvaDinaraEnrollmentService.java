package org.dzhailauvadinara.university.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzhailauvadinara.university.dto.EnrollmentDTO;
import org.dzhailauvadinara.university.entity.DzhailauvaDinaraCourse;
import org.dzhailauvadinara.university.entity.DzhailauvaDinaraEnrollment;
import org.dzhailauvadinara.university.entity.DzhailauvaDinaraStudent;
import org.dzhailauvadinara.university.exception.ResourceNotFoundException;
import org.dzhailauvadinara.university.repository.CourseRepository;
import org.dzhailauvadinara.university.repository.EnrollmentRepository;
import org.dzhailauvadinara.university.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DzhailauvaDinaraEnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final DzhailauvaDinaraEmailService emailService;

    public EnrollmentDTO enroll(Long studentId, Long courseId) {
        DzhailauvaDinaraStudent student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        DzhailauvaDinaraCourse course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        DzhailauvaDinaraEnrollment e = DzhailauvaDinaraEnrollment.builder()
                .student(student).course(course)
                .enrolledAt(LocalDateTime.now()).build();
        DzhailauvaDinaraEnrollment saved = enrollmentRepository.save(e);
        emailService.sendEnrollmentEmail(student.getEmail(), student.getFirstName(), course.getTitle());
        log.info("Student {} enrolled in course {}", studentId, courseId);
        return toDTO(saved);
    }

    public List<EnrollmentDTO> getByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EnrollmentDTO updateGrade(Long id, Double grade) {
        DzhailauvaDinaraEnrollment e = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        e.setGrade(grade);
        emailService.sendGradeNotification(e.getStudent().getEmail(), grade);
        log.info("Grade {} set for enrollment {}", grade, id);
        return toDTO(enrollmentRepository.save(e));
    }

    public void delete(Long id) { enrollmentRepository.deleteById(id); }

    private EnrollmentDTO toDTO(DzhailauvaDinaraEnrollment e) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(e.getId());
        dto.setStudentId(e.getStudent().getId());
        dto.setCourseId(e.getCourse().getId());
        dto.setEnrolledAt(e.getEnrolledAt());
        dto.setGrade(e.getGrade());
        return dto;
    }
}
package org.dzhailauvadinara.university.service;
import org.dzhailauvadinara.university.dto.*;
import org.dzhailauvadinara.university.entity.DzhailauvaDinaraStudent;
import org.dzhailauvadinara.university.exception.ResourceNotFoundException;
import org.dzhailauvadinara.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DzhailauvaDinaraStudentService {

    private final StudentRepository studentRepository;

    public Page<StudentDTO> getAll(int page, int size, String sortBy, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<DzhailauvaDinaraStudent> result = (search != null && !search.isEmpty())
                ? studentRepository.findByFirstNameContainingIgnoreCase(search, pageable)
                : studentRepository.findAll(pageable);
        return result.map(this::toDTO);
    }

    public StudentDTO getById(Long id) {
        return toDTO(studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id)));
    }

    public StudentDTO create(StudentRequestDTO dto) {
        DzhailauvaDinaraStudent s = DzhailauvaDinaraStudent.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
        log.info("Creating student: {}", dto.getEmail());
        return toDTO(studentRepository.save(s));
    }

    public StudentDTO update(Long id, StudentRequestDTO dto) {
        DzhailauvaDinaraStudent s = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        return toDTO(studentRepository.save(s));
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
    private StudentDTO toDTO(DzhailauvaDinaraStudent s) {
        return StudentDTO.builder()
                .id(s.getId())
                .firstName(s.getFirstName())
                .lastName(s.getLastName())
                .email(s.getEmail())
                .build();
    }
}

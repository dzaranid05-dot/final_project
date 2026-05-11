package org.dzhailauvadinara.university.dto;

import org.dzhailauvadinara.university.entity.DzhailauvaDinaraUser;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @Email @NotBlank private String email;
    @NotBlank private String password;
    @NotNull private DzhailauvaDinaraUser.Role role;
}
package com.alexd.movieS.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
	@NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;
}

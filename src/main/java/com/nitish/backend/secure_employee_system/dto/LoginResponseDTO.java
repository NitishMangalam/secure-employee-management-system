package com.nitish.backend.secure_employee_system.dto;

public class LoginResponseDTO {
    private String token;
    public LoginResponseDTO() {}
    public LoginResponseDTO(String token) {
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

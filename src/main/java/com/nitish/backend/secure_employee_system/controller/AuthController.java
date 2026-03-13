
package com.nitish.backend.secure_employee_system.auth;

import com.nitish.backend.secure_employee_system.dto.LoginRequestDTO;
import com.nitish.backend.secure_employee_system.dto.LoginResponseDTO;
import com.nitish.backend.secure_employee_system.response.ApiResponse;
import com.nitish.backend.secure_employee_system.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getUsername());

        LoginResponseDTO response = new LoginResponseDTO(token);

        return new ApiResponse<>(
                true,
                "Login successful",
                response
        );
    }
}
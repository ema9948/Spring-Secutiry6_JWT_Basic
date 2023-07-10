package com.security.demo.Controllers;

import com.security.demo.DTO.ResponseAuth;
import com.security.demo.DTO.UsuarioDTO;
import com.security.demo.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("authentication")
    public ResponseEntity authentication(@RequestBody UsuarioDTO user) {
        ResponseAuth responseAuth = authService.authentication(user);
        return new ResponseEntity<>(responseAuth, responseAuth.getCode());
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody UsuarioDTO user) {
        ResponseAuth responseAuth = authService.register(user);

        return new ResponseEntity<>(responseAuth, responseAuth.getCode());
    }

}

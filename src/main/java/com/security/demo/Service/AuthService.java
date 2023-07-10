package com.security.demo.Service;

import com.security.demo.DTO.ResponseAuth;
import com.security.demo.DTO.UsuarioDTO;
import com.security.demo.JWTConfig.JWTService;
import com.security.demo.Model.Usuario;
import com.security.demo.Repository.UsuarioRep;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRep usuarioRep;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JWTService jwtService;

    public ResponseAuth authentication(UsuarioDTO usuario) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
            return ResponseAuth.builder().code(HttpStatus.OK).jwt(jwtService.generateToken(usuario.getEmail())).build();
        } catch (RuntimeException e) {
            return ResponseAuth.builder().code(HttpStatus.BAD_REQUEST).build();
        }

    }

    public ResponseAuth register(UsuarioDTO user) {
        try {
            usuarioRep.save(
                    Usuario.builder().email(user.getEmail()).password(encoder.encode(user.getPassword())).rol("ADMIN").build()
            );
            return ResponseAuth.builder().code(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseAuth.builder().code(HttpStatus.BAD_REQUEST).build();

        }
    }
}

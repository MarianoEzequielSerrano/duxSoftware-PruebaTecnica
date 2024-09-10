package com.software.dux.challenge.auth;

import com.software.dux.challenge.jwt.JwtService;
import com.software.dux.challenge.model.ErrorMensaje;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "API para la autenticación de usuarios.")

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
    
    @Operation(summary = "Autenticación de usuario.", description = "Autenticación de usuario.")
    @ApiResponse(responseCode = "200", description = "Retorna token para usuario valido.")
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        String userName = request.getUsername();
        String password = request.getPassword();
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.getToken(userDetails.getUsername());
            HashMap<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            return new ResponseEntity(new ErrorMensaje("Credenciales incorrectas", 401), HttpStatus.UNAUTHORIZED);
        }
    }

}

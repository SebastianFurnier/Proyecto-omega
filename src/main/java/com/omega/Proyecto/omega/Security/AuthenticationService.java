package com.omega.Proyecto.omega.Security;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Model.AuthenticationResponse;
import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Repository.IRepositoryEmployee;
import com.omega.Proyecto.omega.Service.IServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final IRepositoryEmployee repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private IServiceEmployee employeeService;

    public AuthenticationService(IRepositoryEmployee repo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Employee request) throws ErrorDataException {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        employeeService.createEmployee(request);
        String token = jwtService.generatorToken(request);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(Employee request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Employee employee = repo.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generatorToken(employee);

        return new AuthenticationResponse(token);
    }
}

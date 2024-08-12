package ir.reyreey.myspringexample.controller;

import ir.reyreey.myspringexample.controller.customization.JwtHandler;
import ir.reyreey.myspringexample.controller.customization.UserAuthenticationInfo;
import ir.reyreey.myspringexample.repository.entities.User;
import ir.reyreey.myspringexample.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 8/2/2024, Friday
 **/
@RestController
@RequestMapping(path = "api/auth",produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHandler jwtHandler;

    @Autowired
    private UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> login(@Valid @RequestBody UserAuthenticationInfo info) {
        var auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(info.getUsername(),
                info.getPassword()));

        var authorities=auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
        var response = new HashMap<String, String>();
        response.put("token", jwtHandler.generateToken(info.getUsername(), authorities));

        return response;
    }

    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.name")
    public User getUserInfo(@PathVariable String username) {
        return (User) service.loadUserByUsername(username);
    }
}

package ir.maktab.securitydemo.controller;

import ir.maktab.securitydemo.dto.RegisterRequest;
import ir.maktab.securitydemo.entity.Address;
import ir.maktab.securitydemo.entity.User;
import ir.maktab.securitydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @GetMapping("/profile")
    public String getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return "Logged in as: " + userDetails.getUsername() + " || " +
                "With Authorities: " + userDetails.getAuthorities() +
                " || " + "With Password: " + userDetails.getPassword();
    }

    @GetMapping("/address")
    public Set<Address> findAllAddress() {
        return userService.findAllUserAddress();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public List<User> getUsers() {
        // ROLE_USER ROLE_ADMIN
        // USER_READ USER_WRITE
        return userService.findAllUsers();
    }

}

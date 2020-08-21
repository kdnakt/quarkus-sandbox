package com.kdnakt.quarkus.spring.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role-based-greeting")
public class RoleBasedGreetingController {

    @PreAuthorize("hasRole('admin')")
    @GetMapping
    public String adminhello() {
        return "hello";
    }

}
package com.devmare.blog_app_apis.controllers;

import com.devmare.blog_app_apis.payloads.AppApiResponse;
import com.devmare.blog_app_apis.payloads.dto.UserDTO;
import com.devmare.blog_app_apis.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "This contains User related API methods")
public class UserController {

    @Autowired
    private UserService userService;

    //! http://localhost:8081/api/users/
    @PostMapping("/")
    @Operation(summary = "Create user API", description = "This is user API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success | Ok"),
                    @ApiResponse(responseCode = "401", description = "Not authorized!"),
                    @ApiResponse(responseCode = "201", description = "New user created"),
            }
    )
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //! http://localhost:8081/api/users/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId) {
        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(updatedUser);
    }

    //! http://localhost:8081/api/users/{userId}
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<AppApiResponse> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(
                new AppApiResponse("User deleted successfully", true),
                HttpStatus.OK
        );
    }

    //! http://localhost:8081/api/users/
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //! http://localhost:8081/api/users/
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}

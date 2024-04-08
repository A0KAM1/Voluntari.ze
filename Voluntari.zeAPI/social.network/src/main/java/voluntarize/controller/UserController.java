package voluntarize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voluntarize.entity.User;
import voluntarize.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService _userService;

    @Operation(summary = "create a new user", tags = "User")
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(_userService.create(user));
    }
}

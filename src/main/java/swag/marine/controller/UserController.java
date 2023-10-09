package swag.marine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.User;
import swag.marine.service.UserService;

@RequestMapping("/marine/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user){
        boolean flag = userService.addUser(user);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        boolean flag = userService.updateUser(user);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
    }
    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody User user){
        boolean flag = userService.passwordCheck(user);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
    @GetMapping("/duplicate/{userId}")
    public ResponseEntity<?> idDuplicateCheck(@PathVariable String userId){
        boolean flag = userService.idDuplicateCheck(userId);

        if(flag) return ResponseEntity.status(HttpStatus.OK).body("duplicate!");

        return ResponseEntity.status(HttpStatus.OK).body("no duplicate!");
    }
}

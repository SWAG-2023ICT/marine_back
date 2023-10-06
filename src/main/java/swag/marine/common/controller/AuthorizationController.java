package swag.marine.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Store;
import swag.marine.model.User;
import swag.marine.serviceImpl.AuthorizationServiceImpl;

@Slf4j
@RequestMapping("/marine/auth")
@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationServiceImpl userService;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody User user){
        boolean flag = userService.login(user);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user){
        log.info("user : {}",user);
        boolean flag = userService.addUser(user);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }

    @PostMapping("/stores")
    public ResponseEntity<?> addStore(@RequestBody Store store){
        boolean flag = userService.addStore(store);
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

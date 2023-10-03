package swag.marine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.User;
import swag.marine.serviceImpl.UserServiceImpl;

@RequestMapping("/marine/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    @PostMapping("")
    public ResponseEntity<?> addUser(User user){
        Integer result = userService.addUser(user);
        if(result > 0) return ResponseEntity.status(HttpStatus.OK).body("정상 처리");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패");
    }

    @GetMapping("/duplicate/{userId}")
    public ResponseEntity<?> idDuplicateCheck(@PathVariable String userId){
        boolean flag = userService.idDuplicateCheck(userId);

        if(flag) return ResponseEntity.status(HttpStatus.OK).body("사용불가");

        return ResponseEntity.status(HttpStatus.OK).body("사용가능");
    }

}

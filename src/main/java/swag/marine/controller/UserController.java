package swag.marine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.User;
import swag.marine.service.UserService;

@RequestMapping("/marine/users")
@RestController
@RequiredArgsConstructor
@Tag(name = "users", description = "유저 관련 API")
public class UserController {
    private final UserService userService;
    
    @Operation(summary = "유저 추가", description = "회원가입 절차를 통해 유저를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저를 정상적으로 등록했습니다.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "유저 추가에 실패했습니다.",
                content = @Content(mediaType = "application/json"))
    })
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user){
        boolean flag = userService.addUser(user);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
    @Operation(summary = "유저 조회", description = "유저 아이디를 통해 유저 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저를 정상적으로 조회했습니다.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class)))
    })
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @Operation(summary = "유저 수정", description = "유저 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저를 정상적으로 수정했습니다.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    })
    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        boolean flag = userService.updateUser(user);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
    @Operation(summary = "로그인", description = "유저 아이디, 비밀번호를 입력받아 로그인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인이 정상적으로 완료되었습니다.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "로그인에 실패했습니다.",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody User user){
        boolean passwordCheck = userService.passwordCheck(user);
        boolean isStore = userService.isStore(user.getUserId());
        if (passwordCheck) {
            if(isStore) return ResponseEntity.status(HttpStatus.OK).body(true);
            else return ResponseEntity.status(HttpStatus.OK).body(false);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
    @Operation(summary = "아이디 중복 검사", description = "유저 아이디룰 입력받아 아이디 중복을 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용 가능한 아이디 입니다.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "사용 중인 아이디입니다.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/duplicate/{userId}")
    public ResponseEntity<?> idDuplicateCheck(@PathVariable String userId){
        boolean flag = userService.idDuplicateCheck(userId);

        if(flag) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("duplicate!");

        return ResponseEntity.status(HttpStatus.OK).body("no duplicate!");
    }
}

package swag.marine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Store;
import swag.marine.model.User;
import swag.marine.model.Wish;
import swag.marine.model.vo.StoreVo;
import swag.marine.service.StoreService;
import swag.marine.service.UserService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequestMapping(value = "/marine/users",produces = "application/json;charset=UTF-8")
@RestController
@RequiredArgsConstructor
@Tag(name = "users", description = "유저 관련 API")
public class UserController {
    private final UserService userService;
    private final StoreService storeService;
    @Operation(summary = "유저 추가", description = "회원가입 절차를 통해 유저를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "유저를 정상적으로 등록했습니다.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "유저 추가에 실패했습니다.",
                content = @Content(mediaType = "application/json"))
    })
    @PostMapping("")
    @Transactional
    public ResponseEntity<?> addUser(@RequestBody User user){
        boolean flag = userService.addUser(user);
        if(flag) return ResponseEntity.status(HttpStatus.CREATED).body("success!");

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
    @Operation(summary = "유저 정보 수정", description = "유저 이름 혹은 전화번호를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저를 정상적으로 수정했습니다.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        boolean flag = userService.updateUser(user);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
    @Operation(summary = "비밀번호 변경", description = "이전 비밀번호와 아이디와 변경 비밀번호를 받아 비밀번호를 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호 변경이 성공적으로 완료되었습니다."),
            @ApiResponse(responseCode = "400",description = "비밀번호 변경에 실패했습니다.")
    })
    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody User user){
        boolean flag = userService.updatePassword(user);
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
    public ResponseEntity<?> login(@RequestBody User user) throws SQLException {
        boolean passwordCheck = userService.passwordCheck(user);
        boolean isStore = userService.isStore(user.getUserId());
        Map<String, Object> response = new HashMap<>();
        if (passwordCheck) {
            if(isStore){
                List<StoreVo> stores = storeService.findStoreByUserId(user.getUserId());
                response.put("userType","store");
                response.put("data",stores.get(0));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("userType","user");
                response.put("data",userService.getUser(user.getUserId()));
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
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
    @Operation(summary = "가게 즐겨찾기", description = "사업자 번호와 유저 아이디를 받아와 즐겨찾기를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "즐겨찾기 추가가 성공적으로 완료했습니다."),
            @ApiResponse(responseCode = "400",description = "즐겨찾기 추가에 실패했습니다.")
    })
    @PostMapping("/wish")
    public ResponseEntity<?> addWish(@RequestBody Wish wish){
        Integer wishId = userService.addWish(wish);
        if(wishId > 0) return ResponseEntity.status(HttpStatus.CREATED).body(wishId);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }

    @Operation(summary = "가게 즐겨찾기 삭제",description = "즐겨찾기 기본키를 받아 즐겨찾기를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "즐겨찾기 삭제에 성공했습니다."),
            @ApiResponse(responseCode = "400",description = "즐겨찾기 삭제에 실패했습니다.")
    })
    @DeleteMapping("/wish")
    public ResponseEntity<?> deleteWish(@RequestBody List<Integer> wishIds){
        boolean flag = userService.deleteWish(wishIds);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }

    @Operation(summary = "즐겨찾기 조회",description = "유저 아이디로 즐겨찾기를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "조회에 성공했습니다."),
            @ApiResponse(responseCode = "400",description = "조회에 실패했습니다.")
    })
    @GetMapping("/wish/{userId}")
    public ResponseEntity<?> getAllWishStore(@PathVariable String userId){
        List<Store> stores = userService.findAllWish(userId);
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }
    @GetMapping("/wish/check")
    public ResponseEntity<?> checkWishStatus(@RequestParam String storeId,String userId){
        Integer wishId = userService.checkWishStatus(storeId,userId);
        if(wishId == null) return ResponseEntity.status(HttpStatus.OK).body(0);
        return ResponseEntity.status(HttpStatus.OK).body(wishId);

    }
}

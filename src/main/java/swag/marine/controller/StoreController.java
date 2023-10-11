package swag.marine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Store;
import swag.marine.service.StoreService;
import swag.marine.service.UserService;

import java.util.List;

@Slf4j
@RequestMapping("/marine/stores")
@RequiredArgsConstructor
@RestController
@Tag(name = "stores" , description = "가게 관련 API")
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;
    @Operation(summary = "모든 가게 조회", description = "모든 가게를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 가게 조회합니다.",
                content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Store.class))))
    })
    @GetMapping("")
    public ResponseEntity<List<Store>> getAllStores(@RequestParam(required = false) int page){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getAllStores(page));
    }
    @Operation(summary = "가게 조회",description = "사업자 번호를 통해 특정가게를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "가게가 정상적으로 조회되었습니다.",
                content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{storeId}")
    public ResponseEntity<Store> findStoreById(@PathVariable String storeId){
        Store store = storeService.findStoreById(storeId);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
    @Operation(summary = "가게 등록",description = "사장님이 새로운 가게를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "가게가 정상적으로 등록되었습니다.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "가가 등록에 실패하였습니다.",
                content = @Content(mediaType = "application/json"))
    })
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> addStore(@RequestBody Store store){
        boolean flag = userService.addUser(store);
        log.info("[fuck] : {}",store);
        log.info("flag : {}",flag);
        if(flag) flag = storeService.addStore(store);

        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
}

package swag.marine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Store;
import swag.marine.service.StoreService;

import java.util.List;

@RequestMapping("/marine/stores")
@RequiredArgsConstructor
@RestController
public class StoreController {
    private final StoreService storeService;
    @GetMapping("")
    public ResponseEntity<List<Store>> getAllStores(@RequestParam int page){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getAllStores(page));
    }
    @GetMapping("/{storeId}")
    public ResponseEntity<?> findStoreById(@PathVariable String storeId){
        Store store = storeService.findStoreById(storeId);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
    @PostMapping("")
    public ResponseEntity<?> addStore(@RequestBody Store store){
        boolean flag = storeService.addStore(store);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
}

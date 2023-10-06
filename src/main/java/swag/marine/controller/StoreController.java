package swag.marine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swag.marine.model.Store;
import swag.marine.serviceImpl.StoreServiceImpl;

@RequestMapping("/marine/store")
@RequiredArgsConstructor
@RestController
public class StoreController {
    private final StoreServiceImpl storeService;
    @PostMapping("/stores")
    public ResponseEntity<?> addStore(@RequestBody Store store){
        boolean flag = storeService.addStore(store);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
}
package swag.marine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Price;
import swag.marine.model.Product;
import swag.marine.service.PriceService;
import swag.marine.service.ProductService;

import java.util.List;

@RequestMapping("/marine/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    PriceService priceService;

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody Product product, @RequestBody List<Price> prices)  {
        int result = 0;
        for(Price price : prices){
            price.setProductId(product.getProductId());
                if(priceService.addPrice(price) == 1){
                    result = 1;
                }
            }
        if(productService.addProduct(product) == 1 && result  == 1){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping("/updateProduct")
    public ResponseEntity updateProduct(@RequestBody Product product, @RequestBody List<Price> prices)  {
        int result = 0;
        for(Price price : prices){
            if(priceService.updatePrice(price)== 1){
                result = 1;
            }
        }
        if(productService.updateProduct(product) == 1 && result == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity deleteProduct(@RequestParam int productId, @RequestParam int priceId)  {

        if(productService.deleteProduct(productId) == 1 && priceService.deletePrice(priceId) == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/selectAllPriceByProductId")
    public List<Price> selectAllPriceByProductId(@RequestParam int productId)  {
        return priceService.selectAllPriceByProductId(productId);
    }

    @PostMapping("/deleteAllPriceByProductId")
    public ResponseEntity deleteAllPriceByProductId(@RequestParam int productId)  {

        if(priceService.deletePrice(productId) == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

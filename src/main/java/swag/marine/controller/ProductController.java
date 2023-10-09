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

@RequestMapping("/marine/product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    PriceService priceService;

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody Product product, @RequestBody Price price)  {
        price.setProduct_id(product.getProduct_id());
        if(productService.addProduct(product) == 1 && priceService.addPrice(price) == 1){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping("/updateDestination")
    public ResponseEntity updateProduct(@RequestBody Product product, @RequestBody Price price)  {
        if(productService.updateProduct(product) == 1 && priceService.updatePrice(price) == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/deleteDestination")
    public ResponseEntity deleteProduct(@RequestParam int product_id, @RequestParam int price_id)  {
        if(productService.deleteProduct(product_id) == 1 && priceService.deletePrice(price_id) == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

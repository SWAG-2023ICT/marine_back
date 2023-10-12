package swag.marine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Price;
import swag.marine.model.Product;
import swag.marine.service.PriceService;
import swag.marine.service.ProductService;

import java.util.List;

@RequestMapping(value = "/marine/product",produces = "application/json;charset=UTF-8")
@RestController
@Tag(name ="product",description = "상품 관련 API")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    PriceService priceService;
    @Operation(summary = "상품 추가", description = "사장님이 상품을 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "상품이 정삭적으로 추가되었습니다.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "상품 추가에 실패했습니다.",
                content = @Content(mediaType = "application/json"))
    })
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

    @Operation(summary = "상품 수정",description = "상품 내용을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "상품이 정상적으로 수정되었습니다.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "상품 수정에 실패했습니다.",
                content = @Content(mediaType = "application/json"))
    })
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
    @Operation(summary = "상품 삭제", description = "사장님이 상품을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "상품이 정상적으로 삭제되었습니다.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "상품 삭제에 실패했습니다.",
                content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/deleteProduct")
    public ResponseEntity deleteProduct(@RequestParam int productId, @RequestParam int priceId)  {
        if(productService.deleteProduct(productId) == 1 && priceService.deletePrice(priceId) == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @Operation(summary = "모든 가격 검색",description = "상품 기본키로 가격을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 가격을 조회합니다.",
                content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Price.class))))
    })
    @PostMapping("/selectAllPriceByProductId")
    public List<Price> selectAllPriceByProductId(@RequestParam int productId)  {
        return priceService.selectAllPriceByProductId(productId);
    }
    @Operation(summary = "모든 가격 삭제",description = "상품 기본키로 가격을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 가격을 삭제합니다.",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/deleteAllPriceByProductId")
    public ResponseEntity deleteAllPriceByProductId(@RequestParam int productId)  {

        if(priceService.deletePrice(productId) == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

package swag.marine.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "상품 Model")
public class Product {
    @Schema(description = "상품 기본키", example = "1")
    private int productId;
    @Schema(description = "원산지", example = "제주산")
    private String origin;
    @Schema(description = "상품 이름", example = "갈치")
    private String productName;
    @Schema(description = "상품 이미지",nullable = true)
    private byte[] productImage;
    @Schema(description = "상품 설명", example = "가을 바다의 왕 은갈치")
    private String description;
    @Schema(description = "상품 판매 여부", example = "true")
    private boolean productStatus;
    @Schema(description = "사업자 번호", example = "111-22-33333")
    private String storeId;
    @Schema(description = "수량", example = "3")
    private int amount;
    private List<Price> prices;
}

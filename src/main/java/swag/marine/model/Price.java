package swag.marine.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "가격 Model")
public class Price {
    @Schema(description = "가격 기본키", example = "1")
    private int priceId;
    @Schema(description = "단위", example = "500g")
    private String unit;
    @Schema(description = "단위별 가격", example = "10000")
    private int priceByUnit;
    @Schema(description = "상품 기본키", example = "12")
    private int productId;
}

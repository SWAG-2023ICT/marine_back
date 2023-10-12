package swag.marine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "주문 Model")
public class Order {
    @Schema(description = "주문 기본키", example = "1")
    private Integer ordersId;
    @Schema(description = "주문 일시", example = "2023-10-10T08:30:50")
    private Date orderDtm;
    @Schema(description = "주문 총액",example = "20000")
    private int totalPrice;
    @Schema(description = "수령인 전화번호", example = "010-1234-5678")
    private String deliveryPhoneNumber;
    @Schema(description = "수령인 이름", example = "김정은")
    private String deliveryTargetName;
    @Schema(description = "배송 상태", example = "1",defaultValue = "1")
    private int deliveryStatus;
    @Schema(description = "주문 상태", example = "1")
    private int orderStatus;
    @Schema(description = "송장 번호", example = "578987908882",minLength = 12,maxLength = 15,nullable = true)
    private String deliveryInvoice;
    @Schema(description = "주문자 아이디", example = "example123")
    private String orderUserId;
    @Schema(description = "사업자 번호", example = "111-22-33333")
    private String storeId;
    @Schema(description = "주소 기본키", example = "1")
    private Integer destinationId;
    private String reason;
    private Date canceledDtm;
    private List<Product> products;
}

package swag.marine.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swag.marine.model.Destination;
import swag.marine.model.Product;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo {
    private Integer ordersId;
    private Date orderDtm;
    private int totalPrice;
    private String deliveryPhoneNumber;
    private String deliveryTargetName;
    private int deliveryStatus;
    private int orderStatus;
    private String deliveryInvoice;
    private String orderUserId;
    private String storeId;
    private String reason;
    private Date canceledDtm;
    private Destination destination;
    private Integer destinationId;
    private List<ProductVo> products;
}

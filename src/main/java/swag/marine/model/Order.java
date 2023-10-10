package swag.marine.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
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
    private Integer destinationId;
    private List<Product> products;
}

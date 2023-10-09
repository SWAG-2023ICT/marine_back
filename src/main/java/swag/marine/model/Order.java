package swag.marine.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class Order {
    Integer ordersId;
    Date orderDtm;
    int totalPrice;
    String deliveryPhoneNumber;
    String deliveryTargetName;
    int deliveryStatus;
    int orderStatus;
    String deliveryInvoice;
    String orderUserId;
    String storeId;
    Integer destinationId;
    List<Product> products;
}

package swag.marine.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    Integer ordersId;
    Date orderDtm;
    int totalPrice;
    int zipCode;
    String deliveryPhoneNumber;
    String deliveryTargetName;
    boolean deliveryStatus;
    String deliveryInvoice;
    String orderUserId;
    String storeId;
    Integer destinationId;
}

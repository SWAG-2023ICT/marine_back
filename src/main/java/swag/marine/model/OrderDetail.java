package swag.marine.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDetail {
    int ordersId;
    int productId;
    int amount;
}

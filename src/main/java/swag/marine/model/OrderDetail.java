package swag.marine.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetail {
    int ordersId;
    int productId;
    int amount;
}

package swag.marine.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    int ordersId;
    int productId;
    int amount;
}

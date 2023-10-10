package swag.marine.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Price {
    private int priceId;
    private String unit;
    private int priceByUnit;
    private int productId;
}

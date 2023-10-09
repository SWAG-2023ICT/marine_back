package swag.marine.model;

import lombok.Data;

@Data
public class Price {
    private int priceId;
    private String unit;
    private int priceByUnit;
    private int productId;
}

package swag.marine.model;

import lombok.Data;

@Data
public class Price {
    private int price_id;
    private String unit;
    private int price_by_unit;
    private int product_id;
}

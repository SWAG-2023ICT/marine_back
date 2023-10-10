package swag.marine.model;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private int productId;
    private String origin;
    private String productName;
    private byte[] productImage;
    private String description;
    private int productStatus;
    private String storeId;
    private int amount;
    private List<Price> prices;
}

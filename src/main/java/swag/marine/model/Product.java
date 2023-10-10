package swag.marine.model;

import lombok.Data;

import java.util.List;

@Data
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

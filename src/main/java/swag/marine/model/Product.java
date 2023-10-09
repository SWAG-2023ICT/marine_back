package swag.marine.model;

import lombok.Data;

import java.sql.Blob;
import java.util.List;

@Data
public class Product {
    private int productId;
    private String origin;
    private String productName;
    private byte[] productImage;
    private String description;
    private int productStatus;
    private int storeId;
    private List<Price> prices;
}

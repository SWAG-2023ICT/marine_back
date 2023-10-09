package swag.marine.model;

import lombok.Data;

import java.sql.Blob;
import java.util.List;

@Data
public class Product {
    private int productId;
    private String origin;
    private String productName;
    private byte[] productImage; // blob 처리를 어떻게 하면 좋을지 모르겠네요...
    private String description;
    private int productStatus;
    private int storeId;
    private List<Price> prices;
}

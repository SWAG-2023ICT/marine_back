package swag.marine.model;

import lombok.Data;

import java.sql.Blob;

@Data
public class Product {
    private int product_id;
    private String origin;
    private String product_name;
    private Blob product_image; // blob 처리를 어떻게 하면 좋을지 모르겠네요...
    private String description;
    private int product_status;
    private int store_id;
}

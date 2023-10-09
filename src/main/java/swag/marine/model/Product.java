package swag.marine.model;

import lombok.Data;

import java.sql.Blob;

@Data
public class Product {
    private int product_id;
    private String origin;
    private String product_name;
    private byte[] product_image;
    private String description;
    private int product_status;
    private int store_id;
}

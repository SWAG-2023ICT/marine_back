package swag.marine.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swag.marine.model.Price;

import javax.sql.rowset.serial.SerialBlob;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVo {
    private int productId;
    private String origin;
    private Integer cultivationType;
    private String productName;
    private SerialBlob productImage;
    private String description;
    private boolean productStatus;
    private String storeId;
    private int amount;
    private List<Price> prices;
}

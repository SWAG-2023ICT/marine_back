package swag.marine.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import swag.marine.model.Order;

import javax.sql.rowset.serial.SerialBlob;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreVo {
    private String storeId;
    private String storeName;
    private String storePhoneNumber;
    private String storeAddress;
    private SerialBlob storeImage;
    private String sellerId;
    private List<OrderVo> orders;
    private List<ProductVo> products;
}

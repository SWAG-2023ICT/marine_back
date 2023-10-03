package swag.marine.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
public class Store extends User{
    private String storeId;
    private String storeName;
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",message = "전화번호 형식이 맞지않습니다.")
    private String storePhoneNumber;
    private String storeAddress;
    private byte[] storeImage;
    private String sellerId;
}

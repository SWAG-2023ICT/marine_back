package swag.marine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "가게 Model")
public class Store extends User{
    @Schema(description = "사업자 번호", example = "111-22-33333")
    private String storeId;
    @Schema(description = "가게 이름", example = "삼천포 회센터")
    private String storeName;
    @Schema(description = "가게 전화번호", example = "010-3832-2715")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",message = "전화번호 형식이 맞지않습니다.")
    private String storePhoneNumber;
    @Schema(description = "가게 주소(상세 주소는 `,` 로 구분)", example = "경남 진주시 호탄길, 삼천포 회센터")
    private String storeAddress;
    @Schema(description = "가게 대표 이미지", nullable = true)
    private byte[] storeImage;
    @Schema(description = "사장님 아이디", example = "example123")
    private String sellerId;
    private List<Order> orders;
    private List<Product> products;

}

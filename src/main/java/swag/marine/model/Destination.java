package swag.marine.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "주소 Model")
public class Destination {
    @Schema(description = "주소 기본키", example = "1")
    private int destinationId;
    @Schema(description = "우편번호", example = "52819")
    private int zipCode;
    @Schema(description = "주소 이름(별칭)", example = "우리집")
    private String destinationName;
    @Schema(description = "주소(상세주소는 `,` 로 구분", example = "경남 진주시 호탄길, 연암아파트")
    private String destinationAddress;
    @Schema(description = "기본 주소지 여부", example = "true")
    private boolean defaultStatus;
    @Schema(description = "유저 아이디", example = "example123")
    private String userId;
}

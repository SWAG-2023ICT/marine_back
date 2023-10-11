package swag.marine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String userId;
    private String password;
    private String name;
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",message = "전화번호 형식이 맞지않습니다.")
    private String phoneNumber;
    private List<Destination> destinations;
}

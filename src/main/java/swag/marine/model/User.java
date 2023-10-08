package swag.marine.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class User {
    private String userId;

    private String password;

    private String name;

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",message = "전화번호 형식이 맞지않습니다.")
    private String phoneNumber;

    private List<Destination> destinations;
}

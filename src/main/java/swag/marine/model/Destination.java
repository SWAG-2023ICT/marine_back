package swag.marine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Destination {
    private int destinationId;
    private String destinationName;
    private String destinationAddress;
    private boolean defaultStatus;
    private String userId;
}

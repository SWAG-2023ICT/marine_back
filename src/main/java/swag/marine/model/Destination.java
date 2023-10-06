package swag.marine.model;

import lombok.Data;

@Data
public class Destination {
    private int destinationId;
    private String destinationName;
    private String destinationAddress;
    private boolean defaultStatus;
    private String userId;
}

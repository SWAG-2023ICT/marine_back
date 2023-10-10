package swag.marine.model;

import lombok.Data;

@Data
public class Destination {
    private int destinationId;
    private int zipCode;
    private String destinationName;
    private String destinationAddress;
    private int defaultStatus;
    private String userId;
}

package swag.marine.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Destination {
    private int destinationId;
    private int zipCode;
    private String destinationName;
    private String destinationAddress;
    private int defaultStatus;
    private String userId;
}

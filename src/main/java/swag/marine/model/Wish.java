package swag.marine.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Wish {
    int wishId;
    String storeId;
    String userId;
}

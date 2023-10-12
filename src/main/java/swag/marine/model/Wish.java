package swag.marine.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wish {
    int wishId;
    String storeId;
    String userId;
}

package swag.marine.model;

import lombok.Data;

@Data
public class Destination {
    private int destination_id;

    private String destination_name;

    private String destination_address;

    private int default_status;

    private String user_id;
}

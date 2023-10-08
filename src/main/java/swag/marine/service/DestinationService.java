package swag.marine.service;

import org.springframework.stereotype.Component;
import swag.marine.model.Destination;

@Component
public interface DestinationService {
    Integer addDestination(Destination destination);
    Integer updateDestination(Destination destination);

    Integer deleteDestination(int destination_id);
}


package swag.marine.service;

import org.springframework.stereotype.Component;
import swag.marine.model.Destination;

import java.util.List;

@Component
public interface DestinationService {
    Integer addDestination(Destination destination);
    Integer updateDestination(Destination destination);

    Integer deleteDestination(int destinationId);
    List<Destination> selectByUserId(String userId);
    boolean updateDefaultStatus(boolean defaultStatus);
}


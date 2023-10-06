package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swag.marine.mapper.DestinationMapper;
import swag.marine.model.Destination;
import swag.marine.service.DestinationService;

@RequiredArgsConstructor
@Service
public class DestinationServiceImpl implements DestinationService {
    private final DestinationMapper mapper;
    @Override
    public boolean addDestination(Destination destination) {
        Integer result = mapper.addDestination(destination);
        return result > 0;
    }
}

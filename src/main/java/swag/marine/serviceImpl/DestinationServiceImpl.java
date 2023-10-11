package swag.marine.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.DestinationMapper;
import swag.marine.model.Destination;
import swag.marine.service.DestinationService;

@Slf4j
@Transactional
@Service("DestinationService")
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    DestinationMapper destinationMapper;

    @Override
    public Integer addDestination(Destination destination) {
        if(destination.getDestinationName() == null){
            destination.setDestinationName(
                    destination.getDestinationAddress().split(",")[0]
            );
        }
        if(destinationMapper.addDestination(destination) > 0){
            return 1;
        }else{
            return 0;
        }


    }

    @Override
    public Integer updateDestination(Destination destination) {
        if (destinationMapper.updateDestination(destination) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Integer deleteDestination(int destinationId) {
        if (destinationMapper.deleteDestination(destinationId) > 0) {
            return 1;
        } else {
            return 0;
        }

    }
}

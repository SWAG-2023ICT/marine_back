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
public class DestinationServiceImpl  implements DestinationService {
    @Autowired
    DestinationMapper destinationMapper;

    @Override
    public Integer addDestination(Destination destination) {
        if(destination.getDestination_address() == null){
            destination.setDestination_address(destination.getUser_id()+"_기본 배송지");
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
    public Integer deleteDestination(int destination_id) {
        if (destinationMapper.deleteDestination(destination_id) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}

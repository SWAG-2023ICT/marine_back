package swag.marine.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.DestinationMapper;
import swag.marine.model.Destination;
import swag.marine.service.DestinationService;

import java.util.List;

@Slf4j
@Transactional
@Service("DestinationService")
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    DestinationMapper destinationMapper;

    @Override
    public Integer addDestination(Destination destination) {
        if(destination.getDestinationName() == null){
            int num = 0;
            if(!selectByUserId(destination.getUserId()).isEmpty()){
                num = selectByUserId(destination.getUserId()).size();
            }
            destination.setDestinationName("배송지-" + num);
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

    @Override
    public List<Destination> selectByUserId(String userId) {
        return destinationMapper.selectByUserId(userId);
    }
}

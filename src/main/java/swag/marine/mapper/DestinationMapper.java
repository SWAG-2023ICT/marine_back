package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Destination;

import java.util.List;

@Mapper
public interface DestinationMapper {
    Integer addDestination(Destination destination);
    Integer updateDestination(Destination destination);
    Integer deleteDestination(int destinationId);
    List<Destination> selectByUserId(String userId);
    Integer getDestinationCount(String userId);
    Integer updateDefaultStatus(Destination destinations);
    Destination getDestinationById(int destinationId);
}

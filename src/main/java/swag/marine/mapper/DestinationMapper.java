package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Destination;

@Mapper
public interface DestinationMapper {
    Integer addDestination(Destination destination);
    Integer updateDestination(Destination destination);

    Integer deleteDestination(int destination_id);

}

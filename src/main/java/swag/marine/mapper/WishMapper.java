package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Wish;

import java.util.List;


@Mapper
public interface WishMapper {
    Integer addWish(Wish wish);
    Integer deleteWish(List<Integer> wishIds);
    List<String> findAllWish(String userId);
}

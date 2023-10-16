package swag.marine.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import swag.marine.model.Order;
import swag.marine.model.Product;
import swag.marine.model.Store;
import swag.marine.model.vo.OrderVo;
import swag.marine.model.vo.ProductVo;
import swag.marine.model.vo.StoreVo;

import javax.sql.rowset.serial.SerialBlob;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class ConvertVo {

    public StoreVo convert(Store store) throws SQLException {
        StoreVo storeVo = StoreVo.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .storePhoneNumber(store.getStorePhoneNumber())
                .storeAddress(store.getStoreAddress())
                .sellerId(store.getSellerId())
                .build();
        try {
            storeVo.setStoreImage(new SerialBlob(store.getStoreImage()));
        }catch (NullPointerException exception){}
        try{
            List<Order> orders = store.getOrders();
            List<OrderVo> orderVoList = new ArrayList<>();
            for(Order order : orders) orderVoList.add(this.convert(order));
            storeVo.setOrders(orderVoList);
        }catch (NullPointerException exception){}
        try{
            List<Product> products = store.getProducts();
            List<ProductVo> productVoList = new ArrayList<>();
            for(Product product : products) productVoList.add(this.convert(product));
            storeVo.setProducts(productVoList);
        }catch (NullPointerException exception){}

        return storeVo;
    }

    public ProductVo convert(Product product) throws SQLException {
        ProductVo productVo =  ProductVo.builder()
                .productId(product.getProductId())
                .origin(product.getOrigin())
                .cultivationType(product.getCultivationType())
                .productName(product.getProductName())
                .description(product.getDescription())
                .productStatus(product.isProductStatus())
                .storeId(product.getStoreId())
                .prices(product.getPrices())
                .build();
        try{
            productVo.setProductImage(new SerialBlob(product.getProductImage()));
        } catch (NullPointerException e){}

        return productVo;
    }

    public OrderVo convert(Order order) throws SQLException{
        OrderVo orderVo = OrderVo.builder()
                .ordersId(order.getOrdersId())
                .orderDtm(order.getOrderDtm())
                .totalPrice(order.getTotalPrice())
                .deliveryPhoneNumber(order.getDeliveryPhoneNumber())
                .deliveryTargetName(order.getDeliveryTargetName())
                .deliveryStatus(order.getDeliveryStatus())
                .orderStatus(order.getOrderStatus())
                .orderUserId(order.getOrderUserId())
                .storeId(order.getStoreId())
                .reason(order.getReason())
                .canceledDtm(order.getCanceledDtm())
                .destination(order.getDestination())
                .build();
        try{
            List<Product> products = order.getProducts();
            List<ProductVo> productVoList = new ArrayList<>();
            for(Product product : products){
                productVoList.add(this.convert(product));
            }
            orderVo.setProducts(productVoList);
        }catch (NullPointerException exception){}
        return orderVo;
    }
}

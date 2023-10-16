package swag.marine.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import swag.marine.model.Product;
import swag.marine.model.Store;
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
            storeVo.setOrders(store.getOrders());
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
}

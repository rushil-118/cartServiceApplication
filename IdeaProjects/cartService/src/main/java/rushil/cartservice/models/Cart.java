package rushil.cartservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Cart {
    Long id;
    Long userId;
    String date;
    List<Product> products;
}

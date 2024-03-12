package rushil.cartservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeStoreCartServiceDTO {
    Long id;
    Long userId;
    String date;
    List<FakeStoreProductDTO> products;
}

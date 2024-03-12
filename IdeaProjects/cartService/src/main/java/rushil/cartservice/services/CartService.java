package rushil.cartservice.services;

import rushil.cartservice.models.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllcarts();
    Cart getSingleCart(Long ID);
    List<Cart> getinDateRange(String startdate, String enddate);
    List<Cart> getUser(Long UserID);
    Cart addCart(Cart cart);
    void updateCart(Cart cart, Long ID);
    void deleteCart(Long ID);
}

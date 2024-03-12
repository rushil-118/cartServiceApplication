package rushil.cartservice.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import rushil.cartservice.models.Cart;
import rushil.cartservice.services.CartService;

import java.util.List;

@RestController
public class CartsController {
    CartService cartService;

    public CartsController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public List<Cart> getAllCarts(){
        return cartService.getAllcarts();
    }

    @GetMapping("/carts/{id}")
    public Cart getSingleCart(@PathVariable("id") Long ID){
        return cartService.getSingleCart(ID);
    }

    @GetMapping("/carts")
    public List<Cart> getinDateRange(@RequestParam("startdate=") String Date1,
                                      @RequestParam("enddate=") String Date2){
        return cartService.getinDateRange(Date1,Date2);
    }

    @GetMapping("/carts/user/{userId}")
    public List<Cart> getUser(@PathVariable("userId") Long UserId){
        return cartService.getUser(UserId);
    }

    @PostMapping("/carts")
    public Cart addCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    @PutMapping("/carts/{id}")
    public void updateCart(@PathVariable("id") Long ID,
                           @RequestBody Cart cart){
        cartService.updateCart(cart,ID);
    }

    @DeleteMapping("/carts/{id}")
    public void deleteCart(@PathVariable("id") Long ID){
        cartService.deleteCart(ID);
    }
}


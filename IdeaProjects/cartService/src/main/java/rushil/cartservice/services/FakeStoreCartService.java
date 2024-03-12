package rushil.cartservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rushil.cartservice.DTO.FakeStoreCartServiceDTO;
import rushil.cartservice.DTO.FakeStoreProductDTO;
import rushil.cartservice.models.Cart;
import rushil.cartservice.models.Product;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreCartService implements CartService{
    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<Cart> getAllcarts() {
        FakeStoreCartServiceDTO[] fakeStoreCartServiceDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/carts",
                FakeStoreCartServiceDTO[].class
        );
        List<Cart> carts = new ArrayList<>();
        for(FakeStoreCartServiceDTO fakeStoreCartServiceDTO1 : fakeStoreCartServiceDTO){
            Cart cart = new Cart();
            cart.setId(fakeStoreCartServiceDTO1.getId());
            cart.setDate(fakeStoreCartServiceDTO1.getDate());
            cart.setUserId(fakeStoreCartServiceDTO1.getUserId());
            List<FakeStoreProductDTO> ProductDTOLIST= fakeStoreCartServiceDTO1.getProducts();
            List<Product> cartProduct = new ArrayList<>();
            for(FakeStoreProductDTO prod : ProductDTOLIST){
                Product product = new Product();
                product.setQuantity(prod.getQuantity());
                product.setProductId(prod.getProductId());
                cartProduct.add(product);
            }
            cart.setProducts(cartProduct);
            carts.add(cart);
        }
        return carts;
    }

    @Override
    public Cart getSingleCart(Long ID) {
        FakeStoreCartServiceDTO fakeStoreCartServiceDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/" + ID,
                FakeStoreCartServiceDTO.class
        );
        Cart cart = new Cart();
        cart.setId(ID);
        cart.setDate(fakeStoreCartServiceDTO.getDate());
        cart.setUserId(fakeStoreCartServiceDTO.getUserId());
        List<FakeStoreProductDTO> ProductDTOLIST= fakeStoreCartServiceDTO.getProducts();
        List<Product> cartProduct = new ArrayList<>();
        for(FakeStoreProductDTO prod : ProductDTOLIST){
            Product product = new Product();
            product.setQuantity(prod.getQuantity());
            product.setProductId(prod.getProductId());
            cartProduct.add(product);
        }
        cart.setProducts(cartProduct);

        return cart;
    }

    @Override
    public List<Cart> getinDateRange(String startdate, String enddate) {
        FakeStoreCartServiceDTO[] fakeStoreCartServiceDTOS = restTemplate.getForObject(
                "https://fakestoreapi.com/carts?startdate="+startdate+"&enddate="+enddate,
                FakeStoreCartServiceDTO[].class
        );
        List<Cart> carts = new ArrayList<>();
        for(FakeStoreCartServiceDTO fakeStoreCartServiceDTO : fakeStoreCartServiceDTOS){
            Cart cart = new Cart();
            cart.setId(fakeStoreCartServiceDTO.getId());
            cart.setDate(fakeStoreCartServiceDTO.getDate());
            cart.setUserId(fakeStoreCartServiceDTO.getUserId());
            List<FakeStoreProductDTO> ProductDTOLIST= fakeStoreCartServiceDTO.getProducts();
            List<Product> cartProduct = new ArrayList<>();
            for(FakeStoreProductDTO prod : ProductDTOLIST){
                Product product = new Product();
                product.setQuantity(prod.getQuantity());
                product.setProductId(prod.getProductId());
                cartProduct.add(product);
            }
            cart.setProducts(cartProduct);
            carts.add(cart);
        }
        return carts;
    }

    @Override
    public List<Cart> getUser(Long UserID) {
        FakeStoreCartServiceDTO[] fakeStoreCartServiceDTOS = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/user/" + UserID,
                FakeStoreCartServiceDTO[].class
        );
        List<Cart> userCarts = new ArrayList<>();
        for(FakeStoreCartServiceDTO fakeStoreCartServiceDTO1 : fakeStoreCartServiceDTOS){
            Cart cart = new Cart();
            cart.setId(fakeStoreCartServiceDTO1.getId());
            cart.setDate(fakeStoreCartServiceDTO1.getDate());
            cart.setUserId(fakeStoreCartServiceDTO1.getUserId());
            List<FakeStoreProductDTO> ProductDTOLIST= fakeStoreCartServiceDTO1.getProducts();
            List<Product> cartProduct = new ArrayList<>();
            for(FakeStoreProductDTO prod : ProductDTOLIST){
                Product product = new Product();
                product.setQuantity(prod.getQuantity());
                product.setProductId(prod.getProductId());
                cartProduct.add(product);
            }
            cart.setProducts(cartProduct);
            userCarts.add(cart);
        }
        return userCarts;
    }

    @Override
    public Cart addCart(Cart cart) {
        FakeStoreCartServiceDTO fakeStoreCartServiceDTO = new FakeStoreCartServiceDTO();
        fakeStoreCartServiceDTO.setDate(cart.getDate());
        fakeStoreCartServiceDTO.setId(cart.getId());
        fakeStoreCartServiceDTO.setUserId(cart.getUserId());
        List<Product> cartProducts = cart.getProducts();
        List<FakeStoreProductDTO> fakeStoreProductlist = new ArrayList<>();
        for(Product prod : cartProducts) {
            FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
            fakeStoreProductDTO.setProductId(prod.getProductId());
            fakeStoreProductDTO.setQuantity(prod.getQuantity());
            fakeStoreProductlist.add(fakeStoreProductDTO);
        }
        fakeStoreCartServiceDTO.setProducts(fakeStoreProductlist);

        restTemplate.put(
                "https://fakestoreapi.com/carts",
                fakeStoreCartServiceDTO
        );

        return cart;
    }

    @Override
    public void updateCart(Cart cart, Long ID) {
        FakeStoreCartServiceDTO fakeStoreCartServiceDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/"+ID,
                FakeStoreCartServiceDTO.class
        );
        fakeStoreCartServiceDTO.setDate(cart.getDate());
        fakeStoreCartServiceDTO.setId(cart.getId());
        fakeStoreCartServiceDTO.setUserId(cart.getUserId());
        List<Product> cartProducts = cart.getProducts();
        List<FakeStoreProductDTO> fakeStoreProductlist = new ArrayList<>();
        for(Product prod : cartProducts) {
            FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
            fakeStoreProductDTO.setProductId(prod.getProductId());
            fakeStoreProductDTO.setQuantity(prod.getQuantity());
            fakeStoreProductlist.add(fakeStoreProductDTO);
        }
        fakeStoreCartServiceDTO.setProducts(fakeStoreProductlist);
        restTemplate.put(
                "https://fakestoreapi.com/carts/"+ID,
                fakeStoreCartServiceDTO
        );
    }

    @Override
    public void deleteCart(Long ID) {
        restTemplate.delete(
                "https://fakestoreapi.com/carts/"+ID
        );
    }
}

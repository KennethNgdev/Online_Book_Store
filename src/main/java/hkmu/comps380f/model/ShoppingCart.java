package hkmu.comps380f.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long shoppingCartId;
    @Column(name = "user_id", insertable=false, updatable=false)
    private long userId; //foreign key
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; //relationship
    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<CartItem> cartItems= new ArrayList<>(); //relationship

    public long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}

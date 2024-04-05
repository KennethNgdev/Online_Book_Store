package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private long cartItemId;
    private int quantity;
    @Column(name = "cart_id", insertable=false, updatable=false)
    private long shoppingCartId; //foreign key
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart shoppingCart; // relationship
    @Column(name = "book_id", insertable=false, updatable=false)
    private long bookId; //foreign key
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book; // relationship

    public long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private long orderItemId;
    private int quantity;
    @Column(name = "book_id", insertable = false, updatable = false)
    private long bookId;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "order_id", insertable = false, updatable = false)
    private long orderId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(){}

    public OrderItem(int quantity, Book book, Order order) {
        this.quantity = quantity;
        this.book = book;
        this.order = order;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

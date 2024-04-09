package hkmu.comps380f.dao.Service;

import hkmu.comps380f.controller.BookController;
import hkmu.comps380f.dao.Repository.AppUserRepository;
import hkmu.comps380f.dao.Repository.BookRepository;
import hkmu.comps380f.dao.Repository.OrderItemRepository;
import hkmu.comps380f.dao.Repository.OrderRepository;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.Order;
import hkmu.comps380f.model.OrderItem;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Resource
    private AppUserRepository appUserRepository;
    @Resource
    private BookRepository bookRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;
    @Transactional
    public long addNewOrder(HttpServletRequest request,String username)
            throws UserNotFound{

        AppUser user = appUserRepository.findByUsername(username).orElse(null);
        if(user == null){
            throw new UserNotFound(username);
        }
        Order newOrder = new Order();
        newOrder.setAppUser(user);
        newOrder.setDeliveryAddress(user.getDeliveryAddress());
        newOrder.setDate(new Date());

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<Long, BookController.BookWithQuantity> cart = (Map<Long, BookController.BookWithQuantity>) session.getAttribute("cart");
        List<OrderItem> orderItems = new ArrayList<>();
        int totalBooks = 0;
        float totalPrice = 0;
        for (Map.Entry<Long, BookController.BookWithQuantity> entry: cart.entrySet()){

            BookController.BookWithQuantity bookWithQuantity = entry.getValue();
            OrderItem orderItem = new OrderItem(bookWithQuantity.getQuantity(),bookWithQuantity.getBook(),newOrder);

            orderItems.add(orderItem);

            totalBooks += bookWithQuantity.getQuantity();
            totalPrice += bookWithQuantity.getBook().getPrice() * bookWithQuantity.getQuantity();
        }

        newOrder.setOrderItems(orderItems);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setTotalQuantity(totalBooks);

        Order savedOrder = orderRepository.save(newOrder);

        return savedOrder.getOrderId();
    }
}

package hkmu.comps380f.controller;

import hkmu.comps380f.dao.Service.AppUserService;
import hkmu.comps380f.dao.Service.BookService;
import hkmu.comps380f.dao.Service.OrderService;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.AppUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    BookService bookService;
    @Resource
    AppUserService appUserService;
    @Resource
    OrderService orderService;

    @GetMapping(value = {"","/","/cart"})
    public String shoppingCart(){
        return "user/cart";
    }

    @GetMapping("/cart/delete/{bookId}")
    public String removeBookInCart(@PathVariable("bookId") Long bookId, HttpServletRequest request) throws BookNotFound {
        if(bookService.getBook(bookId) == null){
            return "redirect:/user/cart";
        }else {
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            Map<Long, BookController.BookWithQuantity> cart = (Map<Long, BookController.BookWithQuantity>) session.getAttribute("cart");
            cart.remove(bookId);
        }
        return "redirect:/user/cart";
    }

    @GetMapping("/checkout")
    public String checkoutDetail(HttpServletRequest request, Principal principal) throws UserNotFound{
        AppUser user = appUserService.getUserByUserName(principal.getName());

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<Long, BookController.BookWithQuantity> cart = (Map<Long, BookController.BookWithQuantity>) session.getAttribute("cart");
        int totalBooks = 0;
        float totalPrice = 0;
        for (Map.Entry<Long, BookController.BookWithQuantity> entry: cart.entrySet()){
            BookController.BookWithQuantity bookWithQuantity = entry.getValue();
            totalBooks += bookWithQuantity.getQuantity();
            totalPrice += bookWithQuantity.getBook().getPrice() * bookWithQuantity.getQuantity();
        }

        request.setAttribute("address", user.getDeliveryAddress());
        request.setAttribute("totalBooks", totalBooks);
        request.setAttribute("totalPrice", totalPrice);
        return "user/checkout";
    }

    @GetMapping("/payment")
    public String payment(HttpServletRequest request,Principal principal) throws UserNotFound {
        long orderId = orderService.addNewOrder(request, principal.getName());

        HttpSession session = request.getSession();
        session.removeAttribute("cart");

        return "redirect:/user/cart?orderId="+ orderId;
    }

    @GetMapping("/order/history")
    public String showOrderHistory(){
        return "user/history";
    }

    @ExceptionHandler({BookNotFound.class, UserNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }
}

package hkmu.comps380f.controller;

import hkmu.comps380f.dao.Service.BookService;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.Book;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    BookService bookService;
    @GetMapping(value = {"","/", "/info", "/info/"})
    public String showAllBook(ModelMap modelMap){
        List<Book> books = bookService.getBooks();
        List<BookWithImageData> booksWithImageData = new ArrayList<>();

        for (Book book : books) {
            byte[] imageData = book.getCoverPhoto();
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            booksWithImageData.add(new BookWithImageData(book, base64Image));
        }

        modelMap.addAttribute("booksWithImageData", booksWithImageData);
        return "book/list";
    }
    @GetMapping("/info/{bookId}")
    public ModelAndView getBookInformation(@PathVariable("bookId") Long bookId,
                                           Principal principal, HttpServletRequest request) throws BookNotFound{
        System.out.println("role admin: " + request.isUserInRole("ROLE_ADMIN"));
        System.out.println("role user: " + request.isUserInRole("ROLE_USER"));

        Book book = bookService.getBook(bookId);
        byte[] imageData = book.getCoverPhoto();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        BookWithImageData bookWithImageData = new BookWithImageData(book,base64Image);
        NewUserComment newUserComment = new NewUserComment();



        newUserComment.setBookId(bookId);

        ModelAndView modelAndView = new ModelAndView("book/info");
        modelAndView.addObject("bookWithImageData", bookWithImageData);
        modelAndView.addObject("newUserComment", newUserComment);

        return modelAndView;
    }


    @PostMapping("/info/{bookId}")
    public String addNewComment(NewUserComment newUserComment, @PathVariable("bookId") Long bookId)
            throws BookNotFound, UserNotFound {
        System.out.println(newUserComment.getBookId());
        System.out.println(newUserComment.getContext());
        System.out.println(newUserComment.getUsername());

        Long newCommentId = bookService.addComment(newUserComment.getBookId(),
                newUserComment.getContext(), newUserComment.getUsername());

        return "redirect:/book/info/"+bookId;
    }

    @GetMapping("/addToCart/{bookId}")
    public String addToCart(@PathVariable("bookId") Long bookId,@RequestParam("quantity") int quantity, HttpServletRequest request) throws BookNotFound {

        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null){
            session.setAttribute("cart", new ConcurrentHashMap<>());
        }
        @SuppressWarnings("unchecked")
        Map<Long, BookWithQuantity> cart = (Map<Long, BookWithQuantity>) session.getAttribute("cart");



        Book book = bookService.getBook(bookId);
        BookWithQuantity bookWithQuantity;
        System.out.println("BookId1 : " + book.getBookId());
        System.out.println("BookId2 : " + bookId);
        System.out.println("length : " + cart.size());
        if (!cart.containsKey(bookId)){
            bookWithQuantity = new BookWithQuantity();
            bookWithQuantity.setBook(book);
            bookWithQuantity.setQuantity(quantity);
            cart.put(bookId, bookWithQuantity);
        }else{
            bookWithQuantity = cart.get(bookId);
            bookWithQuantity.addQuantity(quantity);

            cart.put(bookId, bookWithQuantity);
        }

        return "redirect:/user/cart";
    }

    @GetMapping("/removeFromCart/{bookId}")
    public String RemoveFromCart(@PathVariable("bookId") Long bookId,@RequestParam("quantity") int quantity, HttpServletRequest request) throws BookNotFound {

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<Long, BookWithQuantity> cart = (Map<Long, BookWithQuantity>) session.getAttribute("cart");

        BookWithQuantity bookWithQuantity = cart.get(bookId);
        int removeQuantity = bookWithQuantity.getQuantity() - quantity;
        if(removeQuantity <= 0){
            cart.remove(bookId);
        }else {
            bookWithQuantity.setQuantity(removeQuantity);
            cart.put(bookId, bookWithQuantity);
        }

        return "redirect:/user/cart";
    }

    @ExceptionHandler({BookNotFound.class, UserNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }

    public static class BookWithImageData {
        private Book book;
        private String imageData;

        public BookWithImageData(Book book, String imageData) {
            this.book = book;
            this.imageData = imageData;
        }

        public Book getBook() {
            return book;
        }

        public String getImageData() {
            return imageData;
        }
    }

    public static class NewUserComment{
        private Long bookId;
        private String username;
        private Long userId;
        private String context;

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class BookWithQuantity{
        Book book;
        Integer quantity;

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public void addQuantity(Integer quantity){
            this.quantity += quantity;
        }
    }
}

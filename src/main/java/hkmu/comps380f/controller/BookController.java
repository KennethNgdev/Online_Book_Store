package hkmu.comps380f.controller;

import hkmu.comps380f.dao.Service.BookService;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.model.Book;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    BookService bookService;
    @GetMapping(value = {"","/"})
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
        Book book = bookService.getBook(bookId);
        byte[] imageData = book.getCoverPhoto();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        BookWithImageData bookWithImageData = new BookWithImageData(book,base64Image);
        NewUserComment newUserComment = new NewUserComment();

        ModelAndView modelAndView = new ModelAndView("book/info");
        modelAndView.addObject("bookWithImageData", bookWithImageData);
        modelAndView.addObject("newUserComment", newUserComment);

        return modelAndView;
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
    }
}

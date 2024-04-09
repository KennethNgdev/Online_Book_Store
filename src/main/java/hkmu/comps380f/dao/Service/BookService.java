package hkmu.comps380f.dao.Service;

import hkmu.comps380f.dao.Repository.AppUserRepository;
import hkmu.comps380f.dao.Repository.BookRepository;
import hkmu.comps380f.dao.Repository.CommentRepository;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Book;
import hkmu.comps380f.model.Comment;
import jakarta.annotation.Resource;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    @Resource
    BookRepository bookRepository;
    @Resource
    AppUserRepository appUserRepository;
    @Resource
    CommentRepository commentRepository;
    @Transactional
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(Long bookId) throws BookNotFound {
        Book book = bookRepository.findById(bookId).orElse(null);
        if(book == null){
            throw new BookNotFound(bookId);
        }
        return book;
    }

    @Transactional
    public Long addComment(Long bookId, String comment, String userName) throws BookNotFound, UserNotFound {
        Comment newComment  = new Comment();

        Book book = bookRepository.findById(bookId).orElse(null);
        AppUser user = appUserRepository.findByUsername(userName).orElse(null);
        if(book == null){
            throw new BookNotFound(bookId);
        }
        if(user == null){
            throw new UserNotFound(userName);
        }
        newComment.setBook(book);
        newComment.setUser(user);
        newComment.setContext(comment);

        Comment savedComment = commentRepository.save(newComment);

        return savedComment.getCommentId();
    }
}

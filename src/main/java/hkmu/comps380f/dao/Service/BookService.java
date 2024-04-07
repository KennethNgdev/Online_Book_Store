package hkmu.comps380f.dao.Service;

import hkmu.comps380f.dao.Repository.BookRepository;
import hkmu.comps380f.exception.BookNotFound;
import hkmu.comps380f.model.Book;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    @Resource
    BookRepository bookRepository;
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
}

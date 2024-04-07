package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long commentId;
    private String context;
    @Column(name = "ticket_id", insertable = false, updatable = false)
    private long bookId; //foreign key
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Book book; //relationship
    @Column(name = "user_id", insertable = false, updatable = false)
    private long userID; //foreign key
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser; //relationship

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }
}

package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private long adminId;
    @Column(name = "user_id", insertable = false, updatable = false)
    private long userId; //foreign key
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;  //relationship

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
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
}

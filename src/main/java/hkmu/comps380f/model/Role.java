package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private long adminId;
    @Column(name = "role")
    private String roleName;
    @Column(name = "user_id", insertable = false, updatable = false)
    private long userId; //foreign key
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;  //relationship

    public Role(){};
    public Role(AppUser appUser, String roleName){
        this.appUser = appUser;
        this.roleName = roleName;
    }

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

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }
}

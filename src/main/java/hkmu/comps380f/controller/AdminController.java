package hkmu.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping(value = {"","/"})
    public String home(){
        return "admin/home";
    }
    @GetMapping("/add_user")
    public ModelAndView addUser(){
        return new ModelAndView("admin/add_user","userForm", new AddUserForm());
    }

    @PostMapping("/add_user")
    @GetMapping("/add_book")
    public String addBook(){
        return "admin/add_book";
    }
    @GetMapping("/list_user")
    public String editUser(){
        return "admin/list_user";
    }
    @GetMapping("/list_book")
    public String editBook(){
        return "admin/list_book";
    }
    public static class AddUserForm{
        private String userName;
        private String firstName;
        private String lastName;
        private String email;
        private String deliveryAddress;
        private String password;
        private String confirmPassword;
        private String role;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}

package hkmu.comps380f.controller;

import hkmu.comps380f.dao.Service.AppUserService;
import hkmu.comps380f.exception.UsernameExist;
import hkmu.comps380f.model.AppUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {
    @Resource
    AppUserService appUserService;
    @GetMapping("/")
    public String index(){
        return "redirect:/book";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView userRegistration(){
        return new ModelAndView("user/register","userRegistrationForm",new UserRegistrationForm());
    }

    @PostMapping("/register")
    public View createUser(UserRegistrationForm userRegistrationForm) throws UsernameExist{
        long userId = appUserService.createUser(userRegistrationForm.getUserName(), userRegistrationForm.getFirstName(),userRegistrationForm.getLastName(),
                userRegistrationForm.getEmail(),userRegistrationForm.getDeliveryAddress(),userRegistrationForm.getPassword());

        return new RedirectView("/login/?id="+userId, true);
    }

    @ExceptionHandler({UsernameExist.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }

    public static class UserRegistrationForm{
        private String userName;
        private String firstName;
        private String lastName;
        private String email;
        private String deliveryAddress;
        private String password;
        private String confirmPassword;

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
    }
}
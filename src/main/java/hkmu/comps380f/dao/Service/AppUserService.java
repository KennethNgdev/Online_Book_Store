package hkmu.comps380f.dao.Service;

import hkmu.comps380f.dao.Repository.AppUserRepository;
import hkmu.comps380f.exception.UserNotFound;
import hkmu.comps380f.exception.UsernameExist;
import hkmu.comps380f.model.AppUser;
import hkmu.comps380f.model.Role;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    @Resource
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User" + username + "Not Found!"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role: appUser.getRoles()){
            System.out.println("role name; "+role.getRoleName());
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

        }
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
    @Transactional
    public long createUser(String username, String firstName, String lastName,
                           String email, String address, String password) throws UsernameExist {
        if(appUserRepository.findByUsername(username).isPresent()){
            throw new UsernameExist(username);
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEmail(email);
        appUser.setDeliveryAddress(address);
        appUser.setPassword("{noop}"+password);

        Role role = new Role();
        role.setRoleName("ROLE_USER");
        role.setUser(appUser);

        appUser.addRole(role);

        AppUser savedUser = appUserRepository.save(appUser);

        return savedUser.getUserId();
    }

    @Transactional
    public AppUser getUserByUserName(String username) throws UserNotFound {

        AppUser user  = appUserRepository.findByUsername(username).orElse(null);

        if(user == null){
            throw new UserNotFound(username);
        }

        return user;
    }
}


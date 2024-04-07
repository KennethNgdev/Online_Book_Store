package hkmu.comps380f.dao.Service;

import hkmu.comps380f.dao.Repository.AppUserRepository;
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
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}

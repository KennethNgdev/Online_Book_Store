package hkmu.comps380f.dao.Repository;

import hkmu.comps380f.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);
    Boolean existsByUsername(String username);
}

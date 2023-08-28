package ma.elaroui.labelvieproduct.security.repositories;

import ma.elaroui.labelvieproduct.security.entites.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String username);
}

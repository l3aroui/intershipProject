package ma.elaroui.labelvieproduct.security.repositories;

import ma.elaroui.labelvieproduct.security.entites.AppUser;
import ma.elaroui.labelvieproduct.security.entites.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRoleRepository extends JpaRepository<AppUserRole,Long> {
    AppUserRole findByRoleName(String roleName);
}


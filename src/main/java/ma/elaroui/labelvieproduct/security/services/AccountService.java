package ma.elaroui.labelvieproduct.security.services;

import ma.elaroui.labelvieproduct.security.entites.AppUser;
import ma.elaroui.labelvieproduct.security.entites.AppUserRole;


import java.util.Date;


public interface AccountService {
    AppUser addNewUser(String userName, String firstName, String lastName, String password, String email, String confirmPassword);
    AppUserRole addNewRole(String roleName);
    void addRoleToUser(String username,String roleName);
    void removeRoleFromUser(String username,String roleName);
    AppUser loadUserByUserName(String userName);
}

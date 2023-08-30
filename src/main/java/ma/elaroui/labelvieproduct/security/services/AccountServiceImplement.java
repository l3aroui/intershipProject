package ma.elaroui.labelvieproduct.security.services;

import ma.elaroui.labelvieproduct.security.entites.AppUser;
import ma.elaroui.labelvieproduct.security.entites.AppUserRole;
import ma.elaroui.labelvieproduct.security.repositories.AppUserRepository;
import ma.elaroui.labelvieproduct.security.repositories.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class AccountServiceImplement implements AccountService {
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImplement(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AppUser addNewUser(String userName, String firstName, String lastName, String password, String email, String confirmPassword) {
        AppUser appUser=appUserRepository.findByUserName(userName);
        if (appUser!=null) throw new RuntimeException("this user already exit");
        if (!password.equals(confirmPassword))throw new RuntimeException("password not match");
        appUser=AppUser.builder()
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(passwordEncoder.encode(password))
                //.birthDay(birthDay)
                .build();
         return appUserRepository.save(appUser);
    }

    @Override
    public AppUserRole addNewRole(String roleName) {
        AppUserRole appUserRole=appUserRoleRepository.findByRoleName(roleName);
        if (appUserRole!=null)throw new RuntimeException("this role already exit");
        appUserRole= AppUserRole.builder()
                .roleName(roleName)
                .build();
        return appUserRoleRepository.save(appUserRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUserName(username);
        AppUserRole appUserRole=appUserRoleRepository.findByRoleName(roleName);
        if (appUser==null)throw new RuntimeException("this user is not exit");
        if (appUserRole==null)throw new RuntimeException("this role is not exit");
        appUser.getRoles().add(appUserRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUserName(username);
        AppUserRole appUserRole=appUserRoleRepository.findByRoleName(roleName);
        if (appUser==null)throw new RuntimeException("this user is not exit");
        if (appUserRole==null)throw new RuntimeException("this role is not exit");
        appUser.getRoles().remove(appUserRole);
    }
    @Override
    public AppUser loadUserByUserName(String userName) {
        return appUserRepository.findByUserName(userName);
    }


}
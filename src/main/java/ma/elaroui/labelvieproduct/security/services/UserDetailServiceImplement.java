package ma.elaroui.labelvieproduct.security.services;

import lombok.AllArgsConstructor;
import ma.elaroui.labelvieproduct.security.entites.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserDetailServiceImplement implements UserDetailsService {
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=accountService.loadUserByUserName(username);
        if (appUser==null)throw new UsernameNotFoundException(String.format("user $s not found",username));

        String[] roles=appUser.getRoles().stream().map(u->u.getRoleName()).toArray(String[]::new);
        UserDetails userDetails= User
                .withUsername(appUser.getUserName())
                .password(appUser.getPassword())
                .roles(roles).build();
        return userDetails;
    }
}

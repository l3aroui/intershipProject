package ma.elaroui.labelvieproduct.web.controllers;


import jakarta.servlet.http.HttpSession;
import ma.elaroui.labelvieproduct.security.entites.AppUser;
import ma.elaroui.labelvieproduct.security.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SecurityController {


    private final AppUserRepository appUserRepository;

    @Autowired
    public SecurityController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }
    @GetMapping("/register")
    public String register(@ModelAttribute AppUser appUser, HttpSession httpSession){
        appUserRepository.save(appUser);
        httpSession.setAttribute("message","User register Successfully.. ");
        return "register";
    }
}

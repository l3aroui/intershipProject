package ma.elaroui.labelvieproduct.web.controllers;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import ma.elaroui.labelvieproduct.security.entites.AppUser;
import ma.elaroui.labelvieproduct.security.repositories.AppUserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class SecurityRestController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AppUserRepository appUserRepository;
    @GetMapping("/profile")
    public String profile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated())throw new RuntimeException("not authenticated");
        String username = authentication.getName();
        AppUser appUser = appUserRepository.findByUserName(username);
        model.addAttribute("userInfo", appUser);
        return "profile";
    }

    @GetMapping(("/changePasswordForm"))
    public String NewPasswordForm(){
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam(name = "oldPassword") String oldPassword, @RequestParam(name = "newPassword") String newPassword, @RequestParam(name = "confirmNewPassword") String confirmNewPassword, Principal principal, HttpSession httpSession){
        String username=principal.getName();
        AppUser currentUser=this.appUserRepository.findByUserName(username);
        if(this.bCryptPasswordEncoder.matches(oldPassword,currentUser.getPassword()) && newPassword.equals(confirmNewPassword)){
            currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.appUserRepository.save(currentUser);
            return "redirect:/profile";
        }else {
            httpSession.setAttribute("message","Password incorrect");
            return "changePassword";
        }
    }
}
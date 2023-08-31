package ma.elaroui.labelvieproduct.web.controllers;

import jakarta.servlet.http.HttpSession;

import ma.elaroui.labelvieproduct.security.repositories.AppUserRepository;
import ma.elaroui.labelvieproduct.security.services.AccountServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SecurityController {



    private final AccountServiceImplement accountServiceImplement;



    private final AppUserRepository appUserRepository;
    @Autowired
    public SecurityController(AccountServiceImplement accountServiceImplement, AppUserRepository appUserRepository) {
        this.accountServiceImplement = accountServiceImplement;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }


    @GetMapping("admin/addUserForm")
    public String addUserForm(){
        return "addUserForm";
    }



    @PostMapping("admin/addUser")
    public String addUser(String userName, String firstName, String lastName, String password, String email, String confirmPassword, HttpSession httpSession){


        if (!password.equals(confirmPassword)) {
            httpSession.setAttribute("error", "Passwords don't match.");

            return "redirect:/admin/addUserForm";
        }
        try {
            accountServiceImplement.addNewUser(userName, firstName, lastName, password, email, confirmPassword);
            return "redirect:/admin/userAdded";
        } catch (Exception e) {
            httpSession.setAttribute("error", "An error occurred while registering the user.");
            return "redirect:/admin/addUserForm";
        }
    }

    @GetMapping("admin/userAdded")
    public String userAdded(){
        return "userAdded";
    }

    @GetMapping("admin/users")
    public String getAllUsers(Model model){
        model.addAttribute("users",appUserRepository.findAll());
        return "allUsers";
    }

}
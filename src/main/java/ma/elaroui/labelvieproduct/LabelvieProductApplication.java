package ma.elaroui.labelvieproduct;


import ma.elaroui.labelvieproduct.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class LabelvieProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabelvieProductApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("zakaria","zakaria","elaroui","12345","zakaria5@gmail.com","12345");
            accountService.addNewUser("omar","omar","falougi","123456","omar5@gmail.com","123456");
            accountService.addRoleToUser("zakaria","ADMIN");
            accountService.addRoleToUser("omar","USER");
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}







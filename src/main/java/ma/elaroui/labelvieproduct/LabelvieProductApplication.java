package ma.elaroui.labelvieproduct;


import ma.elaroui.labelvieproduct.models.entities.Product;
import ma.elaroui.labelvieproduct.repositories.ProductRepository;
import ma.elaroui.labelvieproduct.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


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
    //@Bean
    CommandLineRunner commandLineRunnerProducts(ProductRepository productRepository){
        return args -> {
            productRepository.save(new Product(1L, "Smartphone", 499.99, 50, true, new Date()));
            productRepository.save(new Product(2L, "Laptop", 999.99, 30, false, new Date()));
            productRepository.save(new Product(3L, "Tablet", 299.99, 40, true, new Date()));
            productRepository.save(new Product(4L, "Headphones", 79.99, 100, false, new Date()));
            productRepository.save(new Product(5L, "Smartwatch", 199.99, 25, true, new Date()));
            productRepository.save(new Product(6L, "Desktop PC", 899.99, 15, false, new Date()));
            productRepository.save(new Product(7L, "Camera", 299.99, 10, true, new Date()));
            productRepository.save(new Product(8L, "Printer", 149.99, 20, false, new Date()));
            productRepository.save(new Product(9L, "Monitor", 249.99, 30, true, new Date()));
            productRepository.save(new Product(10L, "Keyboard", 29.99, 50, false, new Date()));
            productRepository.save(new Product(11L, "Mouse", 19.99, 60, true, new Date()));
            productRepository.save(new Product(12L, "External Hard Drive", 89.99, 40, false, new Date()));
            productRepository.save(new Product(13L, "Printer Ink", 19.99, 200, true, new Date()));
            productRepository.save(new Product(14L, "Desk Chair", 149.99, 15, false, new Date()));
            productRepository.save(new Product(15L, "Desk Lamp", 39.99, 30, true, new Date()));
            productRepository.save(new Product(16L, "Coffee Maker", 59.99, 10, false, new Date()));
            productRepository.save(new Product(17L, "Toaster", 29.99, 20, true, new Date()));
            productRepository.save(new Product(18L, "Blender", 49.99, 15, false, new Date()));
            productRepository.save(new Product(19L, "Microwave", 79.99, 10, true, new Date()));
            productRepository.save(new Product(20L, "Vacuum Cleaner", 129.99, 5, false, new Date()));
            productRepository.save(new Product(21L, "Iron", 39.99, 10, true, new Date()));
            productRepository.save(new Product(22L, "Hair Dryer", 19.99, 20, false, new Date()));
            productRepository.save(new Product(23L, "Electric Fan", 59.99, 15, true, new Date()));
            productRepository.save(new Product(24L, "Backpack", 49.99, 25, false, new Date()));
            productRepository.save(new Product(25L, "Messenger Bag", 39.99, 20, true, new Date()));
            productRepository.save(new Product(26L, "Sneakers", 69.99, 30, false, new Date()));
            productRepository.save(new Product(27L, "Running Shoes", 79.99, 25, true, new Date()));
            productRepository.save(new Product(28L, "Hiking Boots", 89.99, 20, false, new Date()));
            productRepository.save(new Product(29L, "Dress Shoes", 59.99, 15, true, new Date()));
            productRepository.save(new Product(30L, "Sunglasses", 29.99, 40, false, new Date()));
            productRepository.save(new Product(31L, "Wristwatch", 149.99, 20, true, new Date()));
            productRepository.save(new Product(32L, "Desk Organizer", 19.99, 30, false, new Date()));
            productRepository.save(new Product(33L, "Plant Pot", 9.99, 50, true, new Date()));
            productRepository.save(new Product(34L, "Picture Frame", 14.99, 40, false, new Date()));
            productRepository.save(new Product(35L, "Candle Holder", 12.99, 60, true, new Date()));
            productRepository.save(new Product(36L, "Throw Pillow", 24.99, 25, false, new Date()));
            productRepository.save(new Product(37L, "Blanket", 39.99, 15, true, new Date()));
            productRepository.save(new Product(38L, "Wall Clock", 29.99, 20, false, new Date()));
            productRepository.save(new Product(39L, "Desk Calendar", 9.99, 40, true, new Date()));
            productRepository.save(new Product(40L, "Bookshelf", 79.99, 10, false, new Date()));
            productRepository.save(new Product(41L, "Office Chair", 149.99, 5, true, new Date()));
            productRepository.save(new Product(42L, "Coffee Table", 89.99, 10, false, new Date()));
            productRepository.save(new Product(43L, "TV Stand", 69.99, 15, true, new Date()));
            productRepository.save(new Product(44L, "Dining Table", 149.99, 10, false, new Date()));
            productRepository.save(new Product(45L, "Bar Stool", 29.99, 20, true, new Date()));
            productRepository.save(new Product(46L, "Couch", 349.99, 5, false, new Date()));
            productRepository.save(new Product(47L, "Throw Rug", 19.99, 10, true, new Date()));
            productRepository.save(new Product(48L, "Bed Frame", 99.99, 15, false, new Date()));
            productRepository.save(new Product(49L, "Mattress", 249.99, 5, true, new Date()));
            productRepository.save(new Product(50L, "Nightstand", 49.99, 20, false, new Date()));
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}







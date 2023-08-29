package ma.elaroui.labelvieproduct.web.controllers;

import jakarta.validation.Valid;
import ma.elaroui.labelvieproduct.models.entities.Product;
import ma.elaroui.labelvieproduct.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/")
    public String home(){
        return "home";
    }


    @GetMapping("/admin/products")
    public String products(Model model, @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name="size",defaultValue = "5") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword){
        Page<Product> productPage=productRepository.findByNameContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listProducts",productPage.getContent());
        model.addAttribute("pages",new int[productPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "products";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id,String keyword,int page){
        productRepository.deleteById(id);
        return "redirect:/admin/products?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/formProducts")
    public String formProducts(Model model){
        model.addAttribute("product",new Product());
        return "formProducts";
    }
    @PostMapping("/admin/save")
    public String save(@Valid Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "formProducts";
        productRepository.save(product);
        return "redirect:/admin/productAdded";
    }
    @GetMapping("/admin/productAdded")
    public String productAdded(){
        return "productAdded";
    }
    @GetMapping("/admin/editProduct")
    public String editProduct(Model model,Long id){
        Product product=productRepository.findById(id).orElse(null);
        if (product==null) throw new RuntimeException("not found");
        model.addAttribute("product",product);
        return "editProducts";
    }

}

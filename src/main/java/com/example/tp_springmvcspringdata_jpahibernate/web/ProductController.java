package com.example.tp_springmvcspringdata_jpahibernate.web;

import com.example.tp_springmvcspringdata_jpahibernate.entities.Product;
import com.example.tp_springmvcspringdata_jpahibernate.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/user/index")
    public String index(Model model){
        List<Product> products =  productRepository.findAll();
        model.addAttribute("productsList",products);
        return  "products";
    }

    @GetMapping("/user/")
    public String Home(){
        return  "redirect:/user/index";
    }
    @PostMapping("/admin/delete")
    public String delete(@RequestParam(name = "id") Long id){//il va chercher dans l'url un parametre id requete para c'est si ce id a un autre nom
        productRepository.deleteById(id);
        return "redirect:/user/index";//redirection
}
    @PostMapping("/admin/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model){
        Product product = productRepository.getReferenceById(id);
        model.addAttribute("product",product);
        return "editProduct";
    }

    @GetMapping("/admin/newproduct")
    public String newproduct(Model model){
       model.addAttribute("product",new Product());
       return  "newProduct";//forword

    }

    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product , BindingResult bindingResult ){
        System.out.println(product);
        // @Valid sert à activer la validation des champs d’un objet sinon il ne va pas les vérifier
        // BindingResult récupère les erreurs.
        if (bindingResult.hasErrors()){ return  "newProduct";}
        productRepository.save(product);
        return  "redirect:/user/index";

    }

    @GetMapping("/user/search")
    public String search(String keyword ,Model model){
        System.out.println(keyword);
        List<Product> products;

        if(keyword != null && !keyword.isEmpty()){
            products = productRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            products = productRepository.findAll();
        }

        model.addAttribute("productsList", products);
        return "products";
    }

    @GetMapping("/user/rénitialiser")
    public String renitialiser(String keyword ){
        return "redirect:/user/index";
    }



    @GetMapping("/notAuthorized")
    public String notAuthorized(Model model){
        return  "notAuthorized";

    }

    @GetMapping("/login")
    public String login(){
        return  "login";

    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();//supprimer la session
        return  "login";

    }
}

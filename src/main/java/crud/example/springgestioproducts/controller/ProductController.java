package crud.example.springgestioproducts.controller;



import crud.example.springgestioproducts.entitys.Products;
import crud.example.springgestioproducts.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @RequestMapping("/")                    //View the list
    public String viewPage(Model model){
        List<Products> productsList = productsService.listAll();
        model.addAttribute("productsList", productsList);
        return "index";
    }

    @RequestMapping("/new")
    public String showFormProduct(Model model){
        Products products = new Products(); //New object that will be safe in the db
        model.addAttribute("products", products);
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewProduct(@ModelAttribute("products") Products products){
        productsService.save(products);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable( name="id") Long id){
        ModelAndView model = new ModelAndView("edit_product"); //Es como Model model pero la vista la damos por parametros
        Products products = productsService.get(id);
        model.addObject("products", products);

        return model;
    }
}

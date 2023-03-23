package crud.example.springgestioproducts.controller;


import crud.example.springgestioproducts.entitys.Product;
import crud.example.springgestioproducts.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public String viewPage(Model model, @Param("keyWord") String keyWord) {
        List<Product> productList = productsService.listAll(keyWord);
        model.addAttribute("productsList", productList);
        model.addAttribute("keyWord", keyWord);
        return "index";
    }

    @RequestMapping("/new")
    public String showFormProduct(Model model) {
        Product product = new Product(); //New object that will be safe in the db
        model.addAttribute("product", product);
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewProduct(@ModelAttribute("product") Product product) {
        productsService.save(product);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable(name = "id") Long id) {
        ModelAndView model = new ModelAndView("edit_product"); //Es como Model model pero la vista la damos por parametros
        Product product = productsService.get(id);
        model.addObject("product", product);

        return model;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productsService.delete(id);
        return "redirect:/";
    }
}

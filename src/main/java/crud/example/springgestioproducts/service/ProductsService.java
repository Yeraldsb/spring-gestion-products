package crud.example.springgestioproducts.service;

import crud.example.springgestioproducts.entitys.Products;
import crud.example.springgestioproducts.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductosRepository productosRepository;

    public List<Products> listAll(String keyWord){
        if (keyWord != null){
            return productosRepository.findAll(keyWord);
        }
        return productosRepository.findAll();
    }

    public void save(Products products){
        productosRepository.save(products);
    }

    public Products get(Long id){
        return productosRepository.findById(id).get();
    }

    public void delete(Long id){
        productosRepository.deleteById(id);

    }
}

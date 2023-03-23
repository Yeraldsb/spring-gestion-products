package crud.example.springgestioproducts.service;

import crud.example.springgestioproducts.entitys.Product;
import crud.example.springgestioproducts.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductosRepository productosRepository;

    public List<Product> listAll(String keyWord){
        if (keyWord != null){
            return productosRepository.findAll(keyWord);
        }
        return productosRepository.findAll();
    }

    public void save(Product product){
        productosRepository.save(product);
    }

    public Product get(Long id){
        return productosRepository.findById(id).get();
    }

    public void delete(Long id){
        productosRepository.deleteById(id);

    }
}

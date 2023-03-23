package crud.example.springgestioproducts.repository;

import crud.example.springgestioproducts.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE " +
            " CONCAT(p.name, p.brand, p.madeIn, p.price) " +
            " LIKE %?1% ")

    public List<Product> findAll(String keyWord);

    public Product findByName(String name);

}

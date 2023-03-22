package crud.example.springgestioproducts.repository;

import crud.example.springgestioproducts.entitys.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepository extends JpaRepository<Products ,Long> {

    @Query("SELECT p FROM Products p WHERE " +
            " CONCAT(p.name, p.brand, p.madeIn, p.price) " +
            " LIKE %?1% ")

    public List<Products> findAll( String keyWord);

    public Products findByName(String name);

}

package crud.example.springgestioproducts.repository;

import crud.example.springgestioproducts.entitys.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Products ,Long> {

}

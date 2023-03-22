package crud.example.springgestioproducts;


import crud.example.springgestioproducts.entitys.Products;
import crud.example.springgestioproducts.repository.ProductosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductTest {

    @Autowired
    private ProductosRepository productosRepository;

    /** Information :
     * 1-  @AutoConfigureTestDatabasereplace = AutoConfigureTestDatabase.Replace.NONE  what it does is
     *     enable the test in to our DB, so if we test our delete method it would be actually deleting
     *     the selected product.
     * 2- @Rollback(false) help ups to keep/save the changes in to our DB while testing
     * */


    @Test
    @Rollback(false)
    public void saveProductTest(){
        Products products = new Products(10L,"Pamela", "HP", "USA", 3200);
        Products savedProduct = productosRepository.save(products);

        assertNotNull(savedProduct);
    }

    @Test
    public void searchProduct(){
        String name = "Pamela";
        Products products = productosRepository.findByName(name);

        assertThat(products.getName()).isEqualTo(name);
    }


}

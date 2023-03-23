package crud.example.springgestioproducts;


import crud.example.springgestioproducts.entitys.Product;
import crud.example.springgestioproducts.repository.ProductosRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

    @Autowired
    private ProductosRepository productosRepository;

    /**
     * Information :
     * 1-  @AutoConfigureTestDatabasereplace = AutoConfigureTestDatabase.Replace.NONE  what it does is
     * enable the test in to our DB, so if we test our delete method it would be actually deleting
     * the selected product.
     * 2- @Rollback(false) help ups to keep/save the changes in to our DB while testing
     */


    @Test
    @Rollback(false)
    @Order(1)
    public void createAndSaveProductTest() {
        Product product = new Product(10L, "Plasma TV", "Apple", "USA", 200);
        Product savedProduct = productosRepository.save(product);

        assertNotNull(savedProduct);
    }

    @Test
    @Order(2)
    public void searchProductByNameTest() {
     /*   assertThrows(NullPointerException.class, ()-> {
            String name = "Phone 222";
            Products products = productosRepository.findByName(name);

            assertThat(products.getName()).isEqualTo(name);
        }, "The product that you are searching for does not exist");
*/
        String name = "Phone 222";
        Product product = productosRepository.findByName(name);

        assertThat(product.getName()).isEqualTo(name);
    }

    /**
     * This test checks if you have a non-existing product. if you pass a value
     * that is already on the DB it will tell you that already exists
     */
    @Test
    public void searchProductByNameIfIsAlreadyOnYourDataBase() {

        String name = "Phone 11";
        Product product = productosRepository.findByName(name);

        assertNull(product);
    }

    /**
     * This test edits the value/values of your field, creating a new string and
     * passing modified values to the constructor method of our Products class.
     * IMPORTANT : using the instance of our object we set the id which identifies
     * in the DB.
     * Although it is very important that after we set the ID we save the
     * changes to our DB calling the save method created in our repository
     */
    @Test
    @Rollback(false)
    @Order(3)
    public void updateProductTest() {
        String productName = "CARMELA";
        Product product = new Product(1l, productName, "HP", "USA", 1500);
       // products.setId(1L);
        productosRepository.save(product);

        //Preguntar Javi como añadir test o exception si el ID no es correcto using AssertsThrows or if any changes has been made

        Product savedProduct = productosRepository.findByName(productName);
        assertThat(savedProduct.getName()).isEqualTo(productName);
    }

    @Test
    @Rollback(false)
    public void updateProductTestAndMakeSureChangesHasBeenMade() {
        String productName = "JAVI";
        Product product = new Product(1l, productName, "HP", "USA", 1500);

        if (productName.equals(product.getName())){
            System.out.println("There has been a problem");
        }else{

            productosRepository.save(product);
            Product savedProduct = productosRepository.findByName(productName);
            assertThat(savedProduct.getName()).isEqualTo(productName);
        }
        // products.setId(1L);


        //Preguntar Javi como añadir test o exception si el ID no es correcto using AssertsThrows or if any changes has been made


    }

    /**
     * This test checks if your list is not empty or grater than 0,if so
     * in your console it would print the values of your list
     * */
    @Test
    @Order(5)
    public void listOfProducts(){
        List<Product> products = productosRepository.findAll();
        for (Product productList : products){
            System.out.println(productList);
        }
        assertThat(products).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void deleteProductTest() {

        Long id =12L;
        boolean itExistBeforeDelete = productosRepository.findById(id).isPresent();
        productosRepository.deleteById(id);

        boolean itDoesNotExistAfterDelete = productosRepository.findById(id).isPresent();

        assertTrue(itExistBeforeDelete);
        assertFalse(itDoesNotExistAfterDelete);
    }
}

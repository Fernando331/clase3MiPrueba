package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Products;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {

    @Query(value = "SELECT * FROM northwind.products WHERE ProductName LIKE ?1%", nativeQuery = true)
    List<Products> buscarTransportistasPorInicioDeNombre(String name);

    List<Products> findByCategory_Categoryid(Integer id);

    @Query(value = "SELECT * FROM northwind.products WHERE CategoryID = ?1", nativeQuery = true)
    List<Products> buscarSiSePuedeBorrar(int id);

}
package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Categories;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Integer> {

    @Query(value = "SELECT * FROM northwind.categories WHERE CategoryName LIKE ?1%", nativeQuery = true)
    List<Categories> buscarTransportistasPorInicioDeNombre(String name);
}

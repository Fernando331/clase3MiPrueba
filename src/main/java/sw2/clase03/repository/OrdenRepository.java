package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Orden;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden,Integer> {


    @Query(value = "SELECT o.* FROM northwind.orders o,northwind.customers c WHERE o.CustomerID=c.CustomerID AND c.CompanyName LIKE ?1%", nativeQuery = true)
    List<Orden> buscarTransportistasPorInicioDeNombre(String name);

}

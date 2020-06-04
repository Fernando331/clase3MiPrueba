package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Customers;

import java.util.List;

@Repository
public interface CustomersRepository extends JpaRepository<Customers,String> {

    @Query(value = "SELECT * FROM northwind.customers WHERE CompanyName LIKE ?1%", nativeQuery = true)
    List<Customers> buscarTransportistasPorInicioDeNombre(String inicio);
}

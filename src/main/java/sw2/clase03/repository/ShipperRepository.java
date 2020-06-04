package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Shipper;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

    List<Shipper> findByCompanyName(String companyname);

    @Query(value = "SELECT * FROM northwind.shippers WHERE CompanyName=?1", nativeQuery = true)
    List<Shipper> buscarTransportistasPorNombre(String name);

    @Query(value = "SELECT * FROM northwind.shippers WHERE CompanyName LIKE ?1% OR ShipperID LIKE ?2%", nativeQuery = true)
    List<Shipper> buscarTransportistasPorInicioDeNombre(String name, String id);

}
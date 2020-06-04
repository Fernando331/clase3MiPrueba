package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Territories;

import java.util.List;

@Repository
public interface TerritoryRepository extends JpaRepository<Territories,String> {

    @Query(value = "SELECT * FROM northwind.territories WHERE RegionID = ?1", nativeQuery = true)
    List<Territories> buscarSiSePuedeBorrarRegion(int id);

    @Query(value = "SELECT * FROM northwind.territories WHERE TerritoryDescription LIKE ?1%", nativeQuery = true)
    List<Territories> buscarTransportistasPorInicioDeNombre(String name);

}

package sw2.clase03.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Region;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

    @Query(value = "SELECT * FROM northwind.region WHERE RegionDescription LIKE ?1%", nativeQuery = true)
    List<Region> buscarTransportistasPorInicioDeNombre(String name);


}

package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Rol;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {
    @Query(value = "SELECT * FROM northwind.rol WHERE nombre LIKE  ?1%", nativeQuery = true)
    List<Rol> buscarRolPorInicioDeNombre(String name);
}
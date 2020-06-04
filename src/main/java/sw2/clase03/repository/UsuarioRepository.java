package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    @Query(value = "SELECT * FROM northwind.usuario WHERE nombre LIKE ?1%", nativeQuery = true)
    List<Usuario> buscarUsuariosPorInicioDeNombre(String name);

    @Query(value = "SELECT * FROM northwind.usuario WHERE idrol = ?1", nativeQuery = true)
    List<Usuario> buscarSiTieneUnRolAsociado(int id);

}
package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Usuario;
import sw2.clase03.entity.UsuarioSession;

@Repository
public interface UsuarioSessionRepository extends JpaRepository<UsuarioSession,Integer> {

    public UsuarioSession findByEmail(String email);

}

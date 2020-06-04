package sw2.clase03.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Suppliers;

@Repository
public interface SupplierRepository extends JpaRepository<Suppliers,Integer> {
}

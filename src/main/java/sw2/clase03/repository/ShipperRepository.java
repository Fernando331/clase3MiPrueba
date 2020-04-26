package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {
}


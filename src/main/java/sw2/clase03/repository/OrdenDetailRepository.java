package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.OrdenDetail;
import sw2.clase03.entity.Shipper;

import java.util.List;

@Repository
public interface OrdenDetailRepository extends JpaRepository<OrdenDetail,Integer> {

    @Query(value = "SELECT * FROM northwind.`order details` WHERE ProductID=?1", nativeQuery = true)
    List<OrdenDetail> buscarDetalleOrdenPorIdProducto(int idProducto);

    @Query(value = "SELECT * FROM northwind.`order details` WHERE OrderID=?1", nativeQuery = true)
    List<OrdenDetail> buscarDetalleOrdenPorIdOrden(int idOrden);

}

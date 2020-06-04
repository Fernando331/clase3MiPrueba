package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.dto.EmpleadoPaisDto;
import sw2.clase03.dto.EmpledosRegionDto;
import sw2.clase03.entity.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
    @Query(value = "SELECT COUNT(et.EmployeeID) AS \"cantidadempleados\", r.RegionDescription AS \"region\" FROM northwind.employeeterritories et, northwind.territories t, northwind.region r WHERE et.TerritoryID = t.TerritoryID AND t.RegionID = r.RegionID\n" +
            "GROUP BY r.RegionDescription", nativeQuery = true)
    List<EmpledosRegionDto> buscarEmpledosPorRegion();

    @Query(value = "SELECT COUNT(EmployeeID) AS \"cantidadempleados\", Country AS \"pais\" FROM northwind.employees GROUP BY Country;", nativeQuery = true)
    List<EmpleadoPaisDto> buscarEmpledosPorPais();

}

package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.TouristicServ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositoryTouristicServ extends JpaRepository<TouristicServ, Long> {
     List<TouristicServ> getTouristicServByActive(boolean active);

     Optional<TouristicServ> getTouristicServByActiveAndIdServ(boolean active, Long idTouristicService);

}

package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.TouristicServ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositoryTouristicServ extends JpaRepository<TouristicServ, Long> {
    @Query("SELECT TS FROM TouristicServ TS WHERE TS.active = true")
    List<TouristicServ> getActiveTouristicServices();

    @Query("SELECT TS FROM TouristicServ TS WHERE TS.active = false")
    List<TouristicServ> getInactiveTouristicServices();

    @Query("SELECT TS FROM TouristicServ TS WHERE TS.active = true AND TS.idTouristicService = :id")
    Optional<TouristicServ> getActiveTouristicServicesById(@Param("id") Long id);

    @Query("SELECT TS FROM TouristicServ TS WHERE TS.active = false AND TS.idTouristicService = :id")
    Optional<TouristicServ> getInactiveTouristicServicesById(@Param("id") Long id);

}

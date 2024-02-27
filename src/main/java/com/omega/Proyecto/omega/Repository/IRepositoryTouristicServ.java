package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.TouristicServ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryTouristicServ extends JpaRepository<TouristicServ, Long> {
    @Query("SELECT T FROM TouristicServ T where T.active = true")
    List<TouristicServ> getActiveTouristicServices();
}

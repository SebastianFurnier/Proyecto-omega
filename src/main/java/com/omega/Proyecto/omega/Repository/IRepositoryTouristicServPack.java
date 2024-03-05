package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.TouristicServPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositoryTouristicServPack extends JpaRepository<TouristicServPack, Long> {
    List<TouristicServPack> getTouristicServsPacksByActive(boolean active);

    Optional<TouristicServPack> getTouristicServPackByActiveAndIdPack(boolean active, Long idPackage);
}

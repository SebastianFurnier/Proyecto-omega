package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositoryTouristicServPackage extends JpaRepository<TouristicServicesPackage, Long> {
    List<TouristicServicesPackage> getTouristicServicesPackagesByActive(boolean active);

    Optional<TouristicServicesPackage> getTouristicServicesPackageByActiveAndIdPackage(boolean active, Long idPackage);
}

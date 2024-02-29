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
    @Query("SELECT TSP FROM TouristicServicesPackage TSP WHERE TSP.active = true")
    List<TouristicServicesPackage> getActiveTouristicServicesPackage();

    @Query("SELECT TSP FROM TouristicServicesPackage TSP WHERE TSP.active = false")
    List<TouristicServicesPackage> getInactiveTouristicServicesPackage();

    @Query("SELECT TSP FROM TouristicServicesPackage TSP WHERE TSP.active = true AND TSP.idPackage = :id")
    Optional<TouristicServicesPackage> getActiveTouristicServicesById(@Param("id") Long id);

    @Query("SELECT TSP FROM TouristicServicesPackage TSP WHERE TSP.active = false AND TSP.idPackage = :id")
    Optional<TouristicServicesPackage> getInactiveTouristicServicesPackageById(@Param("id") Long id);
}

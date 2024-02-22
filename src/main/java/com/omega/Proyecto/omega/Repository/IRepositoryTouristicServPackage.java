package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.TouristicServicesPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryTouristicServPackage extends JpaRepository<TouristicServicesPackage, Long> {
}

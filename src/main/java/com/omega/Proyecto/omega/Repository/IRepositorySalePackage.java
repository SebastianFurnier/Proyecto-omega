package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.ServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorySalePackage extends JpaRepository<ServicePackage, Long> {
}

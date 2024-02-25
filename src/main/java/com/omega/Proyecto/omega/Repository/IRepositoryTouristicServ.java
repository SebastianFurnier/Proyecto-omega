package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.TouristicServ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryTouristicServ extends JpaRepository<TouristicServ, Long> {
}

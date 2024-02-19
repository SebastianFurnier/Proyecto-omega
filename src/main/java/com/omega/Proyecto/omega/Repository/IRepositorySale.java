package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorySale extends JpaRepository<Sale, Long> {
}

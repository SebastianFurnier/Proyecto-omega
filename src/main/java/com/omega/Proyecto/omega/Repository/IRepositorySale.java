package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositorySale extends JpaRepository<Sale, Long> {

    List<Sale> getSaleByActive(boolean active);

    Optional<Sale> getSalesByActiveAndIdSale(boolean active, Long idSale);
}

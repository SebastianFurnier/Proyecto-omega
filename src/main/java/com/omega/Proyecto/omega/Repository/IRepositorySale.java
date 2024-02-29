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
    @Query("SELECT S FROM Sale S WHERE S.active = true")
    List<Sale> getActivaSale();

    @Query("SELECT S FROM Sale S WHERE S.active = false")
    List<Sale> getInactiveSale();

    @Query("SELECT S FROM Sale S WHERE S.active = true AND S.idSale = :id")
    Optional<Sale> getActiveSaleById(@Param("id") Long id);

    @Query("SELECT S FROM Sale S WHERE S.active = false AND S.idSale = :id")
    Optional<Sale> getInactiveSaleById(@Param("id") Long id);
}

package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryClient extends JpaRepository<Client,Long> {
}
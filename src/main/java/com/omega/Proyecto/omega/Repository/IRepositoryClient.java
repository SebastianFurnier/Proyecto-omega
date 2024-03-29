package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface IRepositoryClient extends JpaRepository<Client, Long> {
    List<Client> getClientsByFlag(boolean flag);

    Optional<Client> getClientByFlagAndId(boolean flag, Long id) throws ObjectNFException;

}

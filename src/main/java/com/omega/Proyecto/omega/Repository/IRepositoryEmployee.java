package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositoryEmployee extends JpaRepository<Employee, Long> {
    List<Employee> getEmployeesByFlag(boolean flag);

    Optional<Employee> getEmployeeByFlagAndId(boolean flag, Long id);
}



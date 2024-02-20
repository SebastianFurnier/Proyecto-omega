package com.omega.Proyecto.omega.Repository;

import com.omega.Proyecto.omega.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryEmployee extends JpaRepository<Employee, Long> {
}

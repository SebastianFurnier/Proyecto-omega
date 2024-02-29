package com.omega.Proyecto.ServicesTest;

import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Repository.IRepositoryEmployee;
import com.omega.Proyecto.omega.Service.ServiceClient;
import com.omega.Proyecto.omega.Service.ServiceEmployee;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ServiceEmployee.class)
@AutoConfigureMockMvc
public class ServiceEmployeeTest {

    @MockBean
    IRepositoryEmployee repoEmployee;

    @Autowired
    private ServiceEmployee servEmployee;
    private final Employee newEmployee = new Employee();


    @Test
    public void createEmployee(){
        Mockito.when(repoEmployee.save(newEmployee)).thenReturn(newEmployee);

        Employee employeeAux = servEmployee.createEmployee(newEmployee);

        Assertions.assertEquals(newEmployee,employeeAux);
    }

    @Test
    public void getEmployee(){
        Long id = 1L;
        Mockito.when(repoEmployee.findById(id)).thenReturn(Optional.of(newEmployee));

        Employee employeeAux = servEmployee.getEmployee(id);

        Assertions.assertEquals(employeeAux,newEmployee);
    }

    @Test
    public void getAllEmployee(){
        List<Employee> employeeList = new ArrayList<>();
        Mockito.when(repoEmployee.findAll()).thenReturn(employeeList);

        List<Employee> employeeListAux = servEmployee.getAllEmployee();

        Assertions.assertEquals(employeeListAux,employeeList);
    }

    @Test
    public void deleteEmployee(){
        Long id = 1L;
        Mockito.doNothing().when(repoEmployee).deleteById(id);

        servEmployee.deleteEmployee(id);

        Mockito.verify(repoEmployee, Mockito.times(1)).deleteById(id);
    }
}

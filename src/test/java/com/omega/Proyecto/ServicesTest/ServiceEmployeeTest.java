package com.omega.Proyecto.ServicesTest;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Repository.IRepositoryEmployee;
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
import java.time.LocalDate;
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
    public void createEmployeeTest() throws ErrorDataException {
        newEmployee.setId(1L);
        newEmployee.setUsername("Aux");
        newEmployee.setDni("12456654");
        newEmployee.setBirthDay(LocalDate.parse("1999-04-23"));
        newEmployee.setNationality("Argentina");
        newEmployee.setPhoneNumber("123456897");
        newEmployee.setEmail("ClientAux@gmail.com");
        newEmployee.setSalary(20000L);

        Mockito.when(repoEmployee.save(newEmployee)).thenReturn(newEmployee);

        Employee emploAux = servEmployee.createEmployee(newEmployee);

        Assertions.assertEquals(newEmployee, emploAux);
    }

    @Test
    public void getEmployeeTest() throws ObjectNFException {
        Long id = 1L;
        Mockito.when(repoEmployee.findById(id)).thenReturn(Optional.of(newEmployee));

        Employee employeeAux = servEmployee.getEmployee(id);

        Assertions.assertEquals(employeeAux,newEmployee);
    }

    @Test
    public void getAllEmployeeTest(){
        List<Employee> employeeList = new ArrayList<>();
        Mockito.when(repoEmployee.findAll()).thenReturn(employeeList);

        List<Employee> employeeListAux = servEmployee.getAllEmployee();

        Assertions.assertEquals(employeeListAux,employeeList);
    }

    @Test (expected = ErrorDataException.class)
    public void createEmployeeTestExceptions() throws ErrorDataException {
        Employee emploAux = new Employee();
        Mockito.when(repoEmployee.save(emploAux)).thenReturn(emploAux);


        emploAux.setId(1L);
        emploAux.setUsername("Aux");
        emploAux.setDni("12456654");
        emploAux.setBirthDay(LocalDate.parse("1999-04-23"));
        emploAux.setNationality("Argentina");
        emploAux.setPhoneNumber("123456897");
        emploAux.setEmail("EmployeeAux@gmail.com");
        emploAux.setSalary(10000L);

        Employee employee = servEmployee.createEmployee(emploAux);

        Assertions.assertEquals(emploAux,employee);
    }

    @Test
    public void deleteEmployeeTest() throws ObjectNFException{
        Employee emplo = new Employee();
        emplo.setId(1L);
        emplo.setFlag(true);
        Mockito.when(repoEmployee.findById(emplo.getId())).thenReturn(Optional.of(emplo));

        servEmployee.deleteEmployee(emplo.getId());

        Assertions.assertFalse(emplo.isFlag());
    }

    @Test (expected = ObjectNFException.class)
    public void getEmployeeByFlagAndIdTest() throws ObjectNFException{
        Long id = 1L;
        Long idAux = 2L;
        Mockito.when(repoEmployee.getEmployeeByFlagAndId(true,id)).thenReturn(Optional.of(newEmployee));

        Assertions.assertEquals(servEmployee.getEmployeeByFlagAndId(true,idAux),newEmployee);
    }

    @Test (expected = ObjectNFException.class)
    public void modifyClientTest() throws ObjectNFException{
        Mockito.when(repoEmployee.save(newEmployee)).thenReturn(newEmployee);
        newEmployee.setId(2L);

        Assertions.assertEquals(servEmployee.getEmployee(1L),newEmployee);
    }

}

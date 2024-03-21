package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.DTO.EmployeeDTO;
import com.omega.Proyecto.omega.DTO.PersonDTO;
import com.omega.Proyecto.omega.DTO.SaleDTO;
import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Client;
import com.omega.Proyecto.omega.Model.Employee;
import com.omega.Proyecto.omega.Model.Sale;
import com.omega.Proyecto.omega.Service.ServiceSale;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sale")
@CrossOrigin(origins = "http://localhost:3000/")
public class ControllerSale {
    @Autowired
    private ServiceSale serviceSale;

    private List<SaleDTO> makeSalesDTOList(List<Sale> saleList){
        return saleList.stream()
                .map(sale -> {
                    PersonDTO clientDTO = new PersonDTO(
                            sale.getClient().getId(),
                            sale.getClient().getEmail()
                    );

                    EmployeeDTO employeeDTO = new EmployeeDTO(
                            sale.getEmployee().getId(),
                            sale.getEmployee().getUsername(),
                            sale.getEmployee().getRol()
                    );
                    return new SaleDTO(
                            sale.getIdSale(),
                            sale.getDateSale(),
                            sale.getPaymentMethod(),
                            employeeDTO,
                            clientDTO,
                            sale.getTouristicServPack(),
                            sale.isActive(),
                            sale.getCost()
                    );
                }).collect(Collectors.toList());
    }

    private SaleDTO makeSaleDTO(Sale sale){
        Client client = sale.getClient();
        Employee employee = sale.getEmployee();

        PersonDTO clientDTO = new PersonDTO(
                client.getId(),
                client.getEmail());

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getUsername(),
                employee.getRol());

        return new SaleDTO(
                sale.getIdSale(),
                sale.getDateSale(),
                sale.getPaymentMethod(),
                employeeDTO,
                clientDTO,
                sale.getTouristicServPack(),
                sale.isActive(),
                sale.getCost()
        );
    }

    @PostMapping("/create")
    public void createSale(@RequestBody Sale sale) throws ErrorDataException, ObjectNFException, MessagingException {
        serviceSale.createSale(sale);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSale(@PathVariable Long id) throws ObjectNFException {
        serviceSale.deleteSale(id);
    }

    @GetMapping("/getActiveSale/{id}")
    public SaleDTO getActiveSale(@PathVariable Long id) throws ObjectNFException {
        Sale sale = serviceSale.getActiveSale(id);
        return makeSaleDTO(sale);
    }

    @GetMapping("/getInactiveSale/{id}")
    public SaleDTO getInactiveSale(@PathVariable Long id) throws ObjectNFException{
        Sale sale = serviceSale.getInactiveSale(id);
        return makeSaleDTO(sale);
    }

    @GetMapping("/getAllActive")
    public List<SaleDTO> getAllActiveSales(){
        List<Sale> saleList = serviceSale.getAllActiveSales();
        return makeSalesDTOList(saleList);
    }

    @GetMapping("/getAllInactive")
    public List<SaleDTO> getAllInactiveSales(){
        List<Sale> saleList = serviceSale.getAllInactiveSales();
        return makeSalesDTOList(saleList);
    }

    @GetMapping("/getAllSale")
    public List<SaleDTO> getAllSale(){
        List<Sale> saleList = serviceSale.getAllSales();
        return makeSalesDTOList(saleList);
    }
}

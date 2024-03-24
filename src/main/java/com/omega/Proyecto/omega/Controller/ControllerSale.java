package com.omega.Proyecto.omega.Controller;

import com.omega.Proyecto.omega.DTO.DraftSaleDTO;
import com.omega.Proyecto.omega.DTO.SaleDTO;
import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.Sale;
import com.omega.Proyecto.omega.Service.ServiceSale;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sale")
@CrossOrigin(origins = {"http://localhost:3000", "http://joni-projects.s3-website.eu-north-1.amazonaws.com"})
public class ControllerSale {
    @Autowired
    private ServiceSale serviceSale;

    private List<SaleDTO> makeSalesDTOList(List<Sale> saleList) {
        return saleList.stream()
                .map(SaleDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public void createSale(@RequestBody DraftSaleDTO sale) throws ErrorDataException, ObjectNFException, MessagingException {
        serviceSale.createSale(sale);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSale(@PathVariable Long id) throws ObjectNFException {
        serviceSale.deleteSale(id);
    }

    @GetMapping("/getActiveSale/{id}")
    public SaleDTO getActiveSale(@PathVariable Long id) throws ObjectNFException {
        return new SaleDTO(serviceSale.getActiveSale(id));
    }

    @GetMapping("/getInactiveSale/{id}")
    public SaleDTO getInactiveSale(@PathVariable Long id) throws ObjectNFException {
        return new SaleDTO(serviceSale.getInactiveSale(id));
    }

    @GetMapping("/getAllActive")
    public List<SaleDTO> getAllActiveSales() {
        List<Sale> saleList = serviceSale.getAllActiveSales();
        return makeSalesDTOList(saleList);
    }

    @GetMapping("/getAllInactive")
    public List<SaleDTO> getAllInactiveSales() {
        List<Sale> saleList = serviceSale.getAllInactiveSales();
        return makeSalesDTOList(saleList);
    }

    @GetMapping("/getAllSale")
    public List<SaleDTO> getAllSale() {
        List<Sale> saleList = serviceSale.getAllSales();
        return makeSalesDTOList(saleList);
    }
}

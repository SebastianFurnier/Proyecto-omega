package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNotFoundException;
import com.omega.Proyecto.omega.Model.Sale;
import com.omega.Proyecto.omega.Repository.IRepositorySale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceSale implements IServiceSale {
    @Autowired
    private IRepositorySale repositorySale;

    private String checkDataSale(Sale sale){

        if(sale.getClient() == null)
            return "The client cannot be empty.";

        if(sale.getDateSale().isAfter(LocalDate.now()))
            return "Incorrect date.";

        if(sale.getEmployee() == null)
            return "Employee cannot be empty";

        if(sale.getTouristicServicesPackage() == null)
            return "Package cannot be empty";

        return null;
    }

    @Override
    public Sale createSale(Sale sale) throws ErrorDataException {
        String errorMessage = checkDataSale(sale);

        if (errorMessage !=  null){
            throw new ErrorDataException(errorMessage,
                    new ExceptionDetails(errorMessage, "error", HttpStatus.BAD_REQUEST));
        }
        sale.setActive(true);
        return repositorySale.save(sale);
    }

    @Override
    public void deleteSale(Long id) throws ObjectNotFoundException {
        Optional<Sale> optionalSale = repositorySale.findById(id);
        optionalSale.orElseThrow(() -> new ObjectNotFoundException("ID not found.",
                new ExceptionDetails("ID not found", "error", HttpStatus.NOT_FOUND)));

        Sale sale =  optionalSale.get();
        sale.setActive(false);

        repositorySale.save(sale);
    }

    @Override
    public Sale getSale(Long id) throws ObjectNotFoundException {
        Optional<Sale> optionalSale = repositorySale.findById(id);
        return optionalSale.orElseThrow(() -> new ObjectNotFoundException("ID not found.",
                new ExceptionDetails("ID not found", "error", HttpStatus.NOT_FOUND)));
    }

    @Override
    public Sale getInactiveSale(Long id){
        return repositorySale.getInactiveSaleById(id);
    }

    @Override
    public List<Sale> getAllSales() {
        return repositorySale.findAll();
    }

    @Override
    public List<Sale> getAllInactiveSales(){
        return repositorySale.getInactiveSale();
    }
}

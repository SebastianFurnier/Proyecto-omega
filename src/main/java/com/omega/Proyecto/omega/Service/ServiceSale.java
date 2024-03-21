package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Error.ErrorDataException;
import com.omega.Proyecto.omega.Error.ExceptionDetails;
import com.omega.Proyecto.omega.Error.ObjectNFException;
import com.omega.Proyecto.omega.Model.*;
import com.omega.Proyecto.omega.Repository.IRepositorySale;
import jakarta.mail.MessagingException;
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
    @Autowired
    private IEmailService emailService;
    @Autowired
    private IServiceClient serviceClient;
    @Autowired
    private IServiceEmployee serviceEmployee;

    private String checkDataSale(Sale sale){

        if(sale.getClient() == null)
            return "The client cannot be empty.";

        if(sale.getDateSale().isAfter(LocalDate.now()))
            return "Incorrect date.";

        if(sale.getEmployee() == null)
            return "Employee cannot be empty";

        if(sale.getTouristicServPack() == null)
            return "Package cannot be empty";

        return null;
    }

    private void buildMail(Sale sale) throws ObjectNFException, MessagingException, ErrorDataException {
        TouristicServPack touristicServPack = sale.getTouristicServPack();

        Client clientAux = serviceClient.getClientByFlagAndId(true, sale.getClient().getId());
        Employee employee = serviceEmployee.getEmployeeByFlagAndId(true, sale.getEmployee().getId());

        if (clientAux == null
                || clientAux.getEmail() == null
                || clientAux.getEmail().isEmpty()
                || touristicServPack == null)
            return;

        List<String> destinations = sale
                .getTouristicServPack()
                .getTouristicServs()
                .stream()
                .map(TouristicServ::getDestination)
                .toList();


        EmailDTO emailDTO = new EmailDTO();

        emailDTO.setDestinations(destinations);
        emailDTO.setClientName(clientAux.getEmail());
        emailDTO.setReceiver(clientAux.getEmail());
        emailDTO.setSellerName(employee.getUsername());
        emailDTO.setCost(sale.getCost());
        emailDTO.setSaleDate(sale.getDateSale());
        emailDTO.setSubject("Hello " + clientAux.getEmail() + ", you have a reservation!");

        emailService.sendMail(emailDTO);
    }

    @Override
    public Sale createSale(Sale sale) throws ErrorDataException, ObjectNFException, MessagingException {
        String errorMessage = checkDataSale(sale);

        if (errorMessage !=  null){
            throw new ErrorDataException(errorMessage,
                    new ExceptionDetails(errorMessage, "error", HttpStatus.BAD_REQUEST));
        }
        sale.setActive(true);

        sale.setCost(sale.getTouristicServPack().getCostPackage());

        if(sale.getClient() != null)
            buildMail(sale);

        return repositorySale.save(sale);
    }

    @Override
    public void deleteSale(Long id) throws ObjectNFException {
        Sale sale = getActiveSale(id);
        sale.setActive(false);

        repositorySale.save(sale);
    }

    @Override
    public Sale getActiveSale(Long id) throws ObjectNFException {
        Optional<Sale> optionalSale =
                repositorySale.getSalesByActiveAndIdSale(true, id);
        return optionalSale.orElseThrow(() -> new ObjectNFException("ID not found.",
                new ExceptionDetails("ID not found", "error", HttpStatus.NOT_FOUND)));
    }

    @Override
    public Sale getInactiveSale(Long id) throws ObjectNFException {
        Optional<Sale> optionalSale =
                repositorySale.getSalesByActiveAndIdSale(false, id);
        return optionalSale.orElseThrow(() -> new ObjectNFException("ID not found.",
                new ExceptionDetails("ID not found", "error", HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<Sale> getAllSales() {
        return repositorySale.findAll();
    }

    @Override
    public List<Sale> getAllActiveSales(){
        return repositorySale.getSaleByActive(true);
    }

    @Override
    public List<Sale> getAllInactiveSales(){
        return repositorySale.getSaleByActive(false);
    }
}

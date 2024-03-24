package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.DTO.DraftSaleDTO;
import com.omega.Proyecto.omega.DTO.EmailDTO;
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
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private IServiceTouristicServPack serviceTouristicServPack;
    @Autowired
    private IServiceTouristicServ serviceTouristicServ;

    private String checkDataSale(Sale sale) {

        if (sale.getClient() == null)
            return "The client cannot be empty.";

        if (sale.getDateSale().isAfter(LocalDate.now()))
            return "Incorrect date.";

        if (sale.getEmployee() == null)
            return "Employee cannot be empty";

        if (sale.getTouristicServPack() == null)
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

        List<String> destinations = touristicServPack
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

    private void discoutAmountServices(TouristicServPack pack) throws ErrorDataException {
        List<TouristicServ> touristicServList = pack.getTouristicServs();
        for (TouristicServ serv : touristicServList) {
            int amountService = serv.getAmountServ();
            serv.setAmountServ(amountService - 1);

            if ((amountService - 1) == 0)
                serv.setActive(false);
            serviceTouristicServ.editService(serv);
        }
    }

    @Override
    public Sale createSale(DraftSaleDTO saleDto) throws ErrorDataException, ObjectNFException, MessagingException {

        Sale sale = new Sale();

        sale.setDateSale(saleDto.getDateSale());
        sale.setPaymentMethod(saleDto.getPaymentMethod());
        sale.setClient(serviceClient.getClient(saleDto.getClientId()));
        sale.setEmployee(serviceEmployee.getEmployee(saleDto.getEmployeeId()));
        sale.setTouristicServPack(serviceTouristicServPack.getActivePackage(saleDto.getPackId()));

        String errorMessage = checkDataSale(sale);

        if (errorMessage != null) {
            throw new ErrorDataException(errorMessage,
                    new ExceptionDetails(errorMessage, "error", HttpStatus.BAD_REQUEST));
        }
        sale.setActive(true);

        sale.setCost(sale.getTouristicServPack().getCostPackage());

        discoutAmountServices(sale.getTouristicServPack());

        repositorySale.save(sale);

        if (sale.getClient() != null)
            buildMail(sale);

        return sale;
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
    public List<Sale> getAllActiveSales() {
        return repositorySale.getSaleByActive(true);
    }

    @Override
    public List<Sale> getAllInactiveSales() {
        return repositorySale.getSaleByActive(false);
    }

    @Override
    public String getSalesToday(Date today) {
        List<Sale> allSales = this.getAllSales();
        List<Sale> salesToday = new ArrayList<>();
        LocalDate now = LocalDate.now();
        float commision;
        float totalCost;
        float totalSales = 0;

        for (Sale s : allSales) {
            if (s.getDateSale().equals(now)) {
                salesToday.add(s);
                if (s.getPaymentMethod().equals(PaymentMethod.DEBIT)) {
                    commision = (s.getCost() * 3) / 100;
                    totalCost = s.getCost() + commision;
                    s.setCost(totalCost);
                }
                if (s.getPaymentMethod().equals(PaymentMethod.CREDIT)) {
                    commision = (s.getCost() * 9) / 100;
                    totalCost = s.getCost() + commision;
                    s.setCost(totalCost);
                }
                if (s.getPaymentMethod().equals(PaymentMethod.TRANSFER)) {
                    commision = (s.getCost() * 2.45f) / 100;
                    totalCost = s.getCost() + commision;
                    s.setCost(totalCost);
                }
            }
        }

        for (Sale s : salesToday) {
            totalSales += s.getCost();
        }

        return "The number of sales made today is: " + salesToday.size() +
                ", and they amounted to a total cost of: $" + totalSales + ",including taxes";
    }

    @Override
    public String getMonthlySales() {
        List<Sale> listSales = this.getAllSales();
        Month today = LocalDate.now().getMonth();
        List<Sale> listSaleTharMonth = new ArrayList<>();
        float commision;
        float totalCost;
        float totalSales = 0;

        for (Sale s : listSales) {
            if (s.getDateSale().getMonth().equals(today)) {
                listSaleTharMonth.add(s);
                if (s.getPaymentMethod().equals(PaymentMethod.DEBIT)) {
                    commision = (s.getCost() * 3) / 100;
                    totalCost = s.getCost() + commision;
                    s.setCost(totalCost);
                }
                if (s.getPaymentMethod().equals(PaymentMethod.CREDIT)) {
                    commision = (s.getCost() * 9) / 100;
                    totalCost = s.getCost() + commision;
                    s.setCost(totalCost);
                }
                if (s.getPaymentMethod().equals(PaymentMethod.TRANSFER)) {
                    commision = (s.getCost() * 2.45f) / 100;
                    totalCost = s.getCost() + commision;
                    s.setCost(totalCost);
                }
            }
        }

        for (Sale s : listSaleTharMonth) {
            totalSales += s.getCost();
        }

        return "The number of sales made month is: " + listSaleTharMonth.size() +
                ", and they amounted to a total cost of: $" + totalSales + ",including taxes";
    }

}

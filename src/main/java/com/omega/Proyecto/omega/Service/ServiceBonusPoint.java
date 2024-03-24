package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.PaymentMethod;
import com.omega.Proyecto.omega.Model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceBonusPoint implements IServiceBonusPoint {

    @Autowired
    private IServiceSale serviceSale;

    @Override
    public String getSalesToday(LocalDate today) {
        List<Sale> allSales = serviceSale.getAllSales();
        List<Sale> salesToday = new ArrayList<>();
        float commision;
        float totalCost;
        float totalSales = 0;

        for (Sale s : allSales) {
            if (s.getDateSale().equals(today)) {
                salesToday.add(s);
                if (s.getPaymentMethod().equals(PaymentMethod.DEBIT)) {
                    commision = (s.getCost() * 3) / 100;
                    totalCost = s.getCost() - commision;
                    s.setCost(totalCost);
                }
                if (s.getPaymentMethod().equals(PaymentMethod.CREDIT)) {
                    commision = (s.getCost() * 9) / 100;
                    totalCost = s.getCost() - commision;
                    s.setCost(totalCost);
                }
                if (s.getPaymentMethod().equals(PaymentMethod.TRANSFER)) {
                    commision = (s.getCost() * 2.45f) / 100;
                    totalCost = s.getCost() - commision;
                    s.setCost(totalCost);
                }
            }
        }

        for (Sale s : salesToday) {
            totalSales += s.getCost();
        }

        return "The number of sales made today is: " + salesToday.size() + ", and they amounted to a total cost of: $" + totalSales + ", including taxes";
    }

    @Override
    public String getMonthlySales() {
        List<Sale> listSales = serviceSale.getAllSales();
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
                    totalCost = s.getCost() - commision;
                    s.setCost(totalCost);
                }
                if (s.getPaymentMethod().equals(PaymentMethod.CREDIT)) {
                    commision = (s.getCost() * 9) / 100;
                    totalCost = s.getCost() - commision;
                    s.setCost(totalCost);
                }
                if (s.getPaymentMethod().equals(PaymentMethod.TRANSFER)) {
                    commision = (s.getCost() * 2.45f) / 100;
                    totalCost = s.getCost() - commision;
                    s.setCost(totalCost);
                }
            }
        }

        for (Sale s : listSaleTharMonth) {
            totalSales += s.getCost();
        }

        return "The number of sales made month is: " + listSaleTharMonth.size() + ", and they amounted to a total cost of: $" + totalSales + ", including taxes";
    }
}

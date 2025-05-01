package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            JOptionPane.showMessageDialog(null, "-- ENTRE COM OS DADOS DO ALUGUEL --");
            String name = JOptionPane.showInputDialog(null, "Modelo do carro: ");
            String startStr = JOptionPane.showInputDialog(null, "Retirada (dd/MM/yyyy hh:mm): ");
            LocalDateTime start = LocalDateTime.parse(startStr, dtf);
            String finishStr = JOptionPane.showInputDialog(null, "Retirada (dd/MM/yyyy hh:mm): ");
            LocalDateTime finish = LocalDateTime.parse(finishStr, dtf);

            CarRental carRental = new CarRental(start, finish, new Vehicle(name));

            String horaStr = JOptionPane.showInputDialog("Entre com o preço por hora:");
            double hora = Double.parseDouble(horaStr);

            String diaStr = JOptionPane.showInputDialog("Entre com o preço por dia:");
            double dia = Double.parseDouble(diaStr);

            RentalService rentalService = new RentalService(hora, dia, new BrazilTaxService());

            rentalService.processInvoice(carRental);

            StringBuilder sb = new StringBuilder();
            sb.append("-- FATURA --\n");
            sb.append("Pagamento básico: R$ ").append(String.format("%.2f", carRental.getInvoice().getBasicPayment())).append("\n");
            sb.append("Imposto: R$ ").append(String.format("%.2f", carRental.getInvoice().getTax())).append("\n");
            sb.append("Pagamento total: R$ ").append(String.format("%.2f", carRental.getInvoice().getTotalPayment()));

            JOptionPane.showMessageDialog(null, sb.toString());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}
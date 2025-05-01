package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("-- Entre com os dados do aluguel -- ");
        System.out.println("Modelo do carro: ");
        String name = br.readLine();
        System.out.println("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(br.readLine(), dtf);
        System.out.println("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(br.readLine(), dtf);


        CarRental carRental = new CarRental(start, finish, new Vehicle(name));

        System.out.println("Entre com o preço por hora: ");
        double hora = Double.parseDouble(br.readLine());
        System.out.println("Entre com o preço por dia: ");
        double dia = Double.parseDouble(br.readLine());

        RentalService rentalService = new RentalService(hora, dia, new BrazilTaxService());

        rentalService.processInvoice(carRental);

        StringBuilder sb = new StringBuilder();

        sb.append("-- FATURA -- ");
        sb.append("\nPagamento básico: " + carRental.getInvoice().getBasicPayment());
        sb.append("\nImposto: " + carRental.getInvoice().getTax());
        sb.append("\nPagamento total: " + carRental.getInvoice().getTotalPayment());

        System.out.println(sb.toString());
    }
}
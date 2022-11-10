package flightmanagementsystem.view;

import flightmanagementsystem.controller.TicketController;
import flightmanagementsystem.model.Flight;
import flightmanagementsystem.model.Passenger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicketView {

    private final Scanner scanner = new Scanner(System.in);
    private final TicketController ticketController;

    TicketView() {
        ticketController = new TicketController(this);
    }

    public void searchTrain() {
        System.out.print("FROM STATION : ");
        String fromStation = scanner.nextLine();
        System.out.print("TO STATION : ");
        String toStation = scanner.nextLine();
        ticketController.checkFlights(fromStation, toStation);
    }

    public void availableFlights(HashMap<Flight, Integer> availableFlight) {
        for (Map.Entry<Flight, Integer> flight : availableFlight.entrySet()) {
            if (flight.getValue() != 0) {
                Flight flight1 = flight.getKey();
                System.out.print("NAME : " + flight1.getFlightName() + ";");
                System.out.print("NUMBER : " + flight1.getFlightNumber() + ";");
                System.out.print("SOURCE :" + flight1.getSource() + ";");
                System.out.print("DESTINATION :" + flight1.getDestination() + ";");
                System.out.print("DEPART TIME : " + flight1.getDepartureTime() + ";");
                System.out.print("ARRIVAL TIME :" + flight1.getArrivalTime() + ";");
                System.out.print("SEATS : " + flight1.getTotalSeats() + ";");
                System.out.print("COST :" + flight1.getCost() + ";");
            }
            System.out.println();
        }
        System.out.print("SELECT TRAIN NUMBER :");
        int number = scanner.nextInt();
        for (Map.Entry<Flight,Integer> flight : availableFlight.entrySet()) {
            if(flight.getKey().getFlightNumber() == number)bookTicket(flight.getKey(),flight.getValue());
        }
    }

    private void bookTicket(Flight key, Integer value) {
        System.out.print("ENTER NUMBER OF TICKETS : ");
        int ticketCount = scanner.nextInt();
        scanner.nextLine();
        while (ticketCount-- > 0){
            Passenger passenger = new Passenger();
            System.out.print("ENTER NAME : ");
            String name = scanner.nextLine();
            passenger.setName(name);
            System.out.print("ENTER GENDER : ");
            String gender = scanner.next();
            passenger.setGender(gender);
            System.out.print("ENTER MOBILE : ");
            String mobile = scanner.next();
            passenger.setPhoneNumber(mobile);
            System.out.print("ENTER AGE :");
            int id = scanner.nextInt();
            passenger.getPassengerId(id);
        }
    }
}

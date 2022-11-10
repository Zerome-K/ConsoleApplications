package flightmanagementsystem.view;

import flightmanagementsystem.controller.FlightController;
import flightmanagementsystem.model.City;
import flightmanagementsystem.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightView {
    private final FlightController flightController;
    private final Scanner scanner = new Scanner(System.in);

    public FlightView() {
        flightController = new FlightController(this);
    }

    public void createFlight() {
        System.out.print("FLIGHT NAME : ");
        String name = scanner.nextLine();
        System.out.print("FLIGHT NUMBER : ");
        String number = scanner.next();
        System.out.print("SOURCE : ");
        String source = scanner.next();
        System.out.print("DESTINATION : ");
        String destination = scanner.next();
        System.out.print("DEPARTURE TIME : ");
        String departureTime = scanner.next();
        System.out.print("ARRIVAL TIME : ");
        String arrivalTime = scanner.next();
        System.out.print("SEATS : ");
        String seats = scanner.next();
        System.out.print("FARE per TICKET : ");
        String fare = scanner.next();
        List<City> via = this.via(source);
        Flight flight = addFlightData(name, number, source, destination, departureTime, arrivalTime, seats, fare, via);
        flightController.addFlight(flight);
    }

    private Flight addFlightData(String name, String number, String source, String destination, String departureTime, String arrivalTime, String seats, String fare, List<City> via) {
        Flight flight = new Flight();
        flight.setFlightName(name);
        flight.setFlightNumber(Integer.parseInt(number));
        flight.setArrivalTime(arrivalTime);
        flight.setDepartureTime(departureTime);
        flight.setTotalSeats(Integer.parseInt(seats));
        flight.setVia(via);
        return flight;
    }

    private List<City> via(String source) {
        System.out.print("NUMBER OF CITIES TRAVELLED (include destination): ");
        int count = scanner.nextInt();
        List<City> via = new ArrayList<>();
        City city = new City();
        city.setCityName(source);
        city.setMilesFromDestination(0);
        via.add(city);
        while (count-- > 0) {
            scanner.nextLine();
            System.out.print("CITY NAME : ");
            String cityName = scanner.nextLine();
            System.out.print("MILES FROM SOURCE : ");
            int milesAway = scanner.nextInt();
            city.setCityName(cityName);
            city.setMilesFromDestination(milesAway);
            via.add(city);
        }
        return via;
    }

    public void routes() {
        System.out.print("ENTER FLIGHT NUMBER : ");
        int number = scanner.nextInt();
        flightController.listRoutes(number);
    }


    public void displayMessage(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        FlightView fv = new FlightView();

    }
}

package flightmanagementsystem.controller;

import flightmanagementsystem.model.City;
import flightmanagementsystem.model.Flight;
import flightmanagementsystem.repository.DataBase;
import flightmanagementsystem.view.TicketView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TicketController {
    private final TicketView ticketView;

    public TicketController(TicketView ticketView) {
        this.ticketView = ticketView;
    }

    public void checkFlights(String fromStation, String toStation) {
        List<Flight> flights = DataBase.getInstance().getFlights();
        HashMap<Flight, Integer> availableFlight = new HashMap<>();
        for (Flight flight : flights) {
            List<City> via = flight.getVia();
            boolean flag = false;
            int miles = 0, start = 0;
            for (City city : via) {
                if (Objects.equals(city.getCityName(), fromStation)) flag = true;
                else if (flag) {
                    miles += city.getMilesFromDestination();
                    if (Objects.equals(city.getCityName(), toStation)) flag = false;
                }
            }
            availableFlight.put(flight,km);
        }
    }

    public void bookFlight(int number) {
        List<Flight> flights = DataBase.getInstance().getFlights();
        for (Flight flight:flights) {
            if(flight.getFlightNumber() == number)
        }
    }
}

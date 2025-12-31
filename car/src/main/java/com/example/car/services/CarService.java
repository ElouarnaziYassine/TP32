package com.example.car.services;

import com.example.car.entities.Car;
import com.example.car.entities.Client;
import com.example.car.models.CarResponse;
import com.example.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8888/SERVICE-CLIENT";

    public List<CarResponse> getAllCars() {
        List<Car> carList = carRepository.findAll();
        ResponseEntity<Client[]> response = restTemplate.getForEntity(this.URL + "/api/client", Client[].class);
        Client[] clientArray = response.getBody();
        return carList.stream().map((Car car) -> convertToResponse(car, clientArray)).toList();
    }

    private CarResponse convertToResponse(Car car, Client[] clientArray) {
        Client matchedClient = Arrays.stream(clientArray)
                .filter(client -> client.getId().equals(car.getOwnerId()))
                .findFirst()
                .orElse(null);

        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(matchedClient)
                .registrationNumber(car.getRegistrationNumber())
                .model(car.getModel())
                .build();
    }


    public CarResponse getCarById(Long id) throws Exception {
        Car car = carRepository.findById(id).orElseThrow(() -> new Exception("Invalid Car Id"));
        Client client = restTemplate.getForObject(this.URL + "/api/client/" + car.getOwnerId(), Client.class);
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(client)
                .registrationNumber(car.getRegistrationNumber())
                .model(car.getModel())
                .build();
    }
}

package com.animal.main.perform;

import org.springframework.web.reactive.function.client.WebClient;
import com.animal.main.Entity.Animal;

import reactor.core.publisher.Mono;

public class AnimalWebClient {

    private final String SERVER_URI = "http://localhost:8080/api/animals";
    private WebClient webClient = WebClient.create();

    public AnimalWebClient() {
        System.out.println("\n ------------ GET ANIMALS BY GENDER Female ------------");
        getAnimalsByGender("Female");
        System.out.println("\n ------------ GET ANIMALS BY RACE raceTest ------------");
        getAnimalsByBreed("raceTest");
        System.out.println("\n ------------ GET ANIMALS NOT RESERVED ------------");

        getNotReservedAnimals();
    }

    private void getAnimalsByGender(String gender) {
        webClient.get()
                .uri(SERVER_URI + "/gender/" + gender)
                .retrieve()
                .bodyToFlux(Animal.class)
                .flatMap(animal -> {
                    printAnimalData(animal);
                    return Mono.empty();
                })
                .blockLast(); // Blocking call for simplicity in this example
    }

    private void getAnimalsByBreed(String breed) {
        webClient.get()
                .uri(SERVER_URI + "/breed/" + breed)
                .retrieve()
                .bodyToFlux(Animal.class)
                .flatMap(animal -> {
                    printAnimalData(animal);
                    return Mono.empty();
                })
                .blockLast();
    }

    private void getNotReservedAnimals() {
        webClient.get()
                .uri(SERVER_URI + "/notReserved")
                .retrieve()
                .bodyToFlux(Animal.class)
                .flatMap(animal -> {
                    printAnimalData(animal);
                    return Mono.empty();
                })
                .blockLast();
    }

    private void printAnimalData(Animal animal) {
        System.out.printf("ID=%d, Name=%s, Gender=%s, Breed=%s, Race=%s, Reserved=%s%n",
                animal.getId(), animal.getName(), animal.getSex(),
                animal.getBreed(), animal.getRace(), animal.getReserved());
    }

    public static void main(String[] args) {
        new AnimalWebClient();
    }
}

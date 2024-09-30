package de.neuefische.springexceptionhandlingtask;

import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) {
        try {
            if (!brand.equals("porsche")) {
                throw new IllegalArgumentException("Only 'porsche' allowed");
            }
            return brand;
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }
}

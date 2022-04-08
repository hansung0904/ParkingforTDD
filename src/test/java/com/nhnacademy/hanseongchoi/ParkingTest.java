package com.nhnacademy.hanseongchoi;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParkingTest {
    ParkingLot parkingLot = new ParkingLot();
    @BeforeEach
    void setUp() {

    }


    @DisplayName("주차장에 차가 들어옴")
    @Test
    void car_is_coming_into_the_parking() {
        String carNumber = "가나다 1234";
        String parkingCode = "A-1";
        Car car = new Car(carNumber);
        assertThat(parkingLot.enterCar(parkingCode,car)
                    .getCarNumber()).isEqualTo(car.getCarNumber());

    }

    @DisplayName("차를 A-1에 주차")
    @Test
    void parking_the_car_specific_area() {
        String parkingCode = "A-1";
        String carNumber = "가나다 1234";
        Car car = new Car(carNumber);
        parkingLot.enterCar(parkingCode, car);
        ParkingSpace parkingSpace = parkingLot.getParkingSpace();
        assertThat(parkingSpace.getCode()).isEqualTo(parkingCode);
    }


}

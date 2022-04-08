package com.nhnacademy.hanseongchoi;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParkingTest {

    @DisplayName("주차장에 차가 들어옴")
    @Test
    void car_is_coming_into_the_parking() {
        ParkingLot parkingLot = new ParkingLot();
        String carNumber = "가나다 1234";
        Car car = new Car(carNumber);
        assertThat(parkingLot.enterCar(car).getCarNumber())
                    .isEqualTo(car.getCarNumber());
    }


}

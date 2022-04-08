package com.nhnacademy.hanseongchoi;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.hanseongchoi.parkingsystem.ParkingSystem;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
        String carNumber = "가나다1234";
        String parkingCode = "A-1";
        Car car = new Car(carNumber);
        assertThat(parkingLot.enterCar(parkingCode,car)
                    .getCarNumber()).isEqualTo(car.getCarNumber());

    }

    @DisplayName("scanner로 차의 정보를 입력받음")
    @Test
    public void car_info_scanner() {
        ParkingSystem parkingSystem = new ParkingSystem();
        String input = "가나다1234";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(input , parkingSystem.scanCarInfo());
    }

    @DisplayName("차를 A-1에 주차")
    @Test
    void parking_the_car_specific_area() {
        String carNumber = "가나다 1234";
        String parkingCode = "A-1";
        Car car = new Car(carNumber);
        parkingLot.enterCar(parkingCode, car);
        ParkingSpace parkingSpace = parkingLot.getParkingSpace();
        assertThat(parkingSpace.getCode()).isEqualTo(parkingCode);
    }

    @DisplayName("주차장에서 차가 나간다")
    @Test
    void pulled_out_parking_lot() {
        String carNumber = "가나다 1234";
        String parkingCode = "A-1";
        Car car = new Car(carNumber);
        parkingLot.outCar(parkingCode, car);
        assertThat(parkingLot.outCar(parkingCode,car)
            .getCarNumber()).isEqualTo(car.getCarNumber());
    }
}

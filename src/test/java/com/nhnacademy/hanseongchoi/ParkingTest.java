package com.nhnacademy.hanseongchoi;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.hanseongchoi.parkinglot.ParkingLot;
import com.nhnacademy.hanseongchoi.parkinglot.ParkingSpace;
import com.nhnacademy.hanseongchoi.settlementsystem.Money;
import com.nhnacademy.hanseongchoi.settlementsystem.User;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
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
        assertThat(parkingLot.enterCar(parkingCode,car,LocalDateTime.now())
                    .getCar().getCarNumber()).isEqualTo(car.getCarNumber());

    }

    @DisplayName("scanner로 차의 정보를 입력받음")
    @Test
    public void car_info_scanner() {
        ParkingLot parkingLot = new ParkingLot();
        String input = "가나다1234";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(input , parkingLot.scanCarInfo());
    }

    @DisplayName("차를 A-1에 주차")
    @Test
    void parking_the_car_specific_area() {
        String carNumber = "가나다1234";
        String parkingCode = "A-1";
        Car car = new Car(carNumber);
        parkingLot.enterCar(parkingCode, car, LocalDateTime.now());
        ParkingSpace parkingSpace = parkingLot.getParkingSpace();
        assertThat(parkingSpace.getCode()).isEqualTo(parkingCode);
    }

    // 얘는 돈이 있어서 차가 나갈 수 있다?
    @DisplayName("주차장에서 차가 나간다")
    @Test
    void pulled_out_parking_lot() {
        String carNumber = "가나다1234";
        String parkingCode = "A-1";
        Car car = new Car(carNumber);
        parkingLot.outCar(parkingCode, car);
        assertThat(parkingLot.outCar(parkingCode,car)
            .getCarNumber()).isEqualTo(car.getCarNumber());
    }

    @DisplayName("차의 주차요금 계산")
    @Test
    void calculate_parking_fee() {
        String carNumber = "가나다1234";
        String parkingCode = "A-1";
        String userId = "최한성";
        long moneyAmount = 0;
        Money money = new Money(moneyAmount);
        User user = new User(userId,money);
        Car car = new Car(carNumber);
        ParkingSpace parkingSpace = parkingLot.enterCar(parkingCode, car, LocalDateTime.now());
        parkingLot.calculate(parkingSpace);

        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(29));
        assertThat(parkingLot.calculate(parkingSpace)).isEqualTo((1000));

        // 30분 주차
        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(30));
        assertThat(parkingLot.calculate(parkingSpace)).isEqualTo((1000));

        // 31분 주차
        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(31));
        assertThat(parkingLot.calculate(parkingSpace)).isEqualTo((1500));

        // 50분 주차
        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(50));
        assertThat(parkingLot.calculate(parkingSpace)).isEqualTo((2000));

        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(61));
        assertThat(parkingLot.calculate(parkingSpace)).isEqualTo((3000));

        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusHours(6));
        assertThat(parkingLot.calculate(parkingSpace)).isEqualTo((10000));
        //-------------------------------------------------------------------------
        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusDays(1));
        assertThat(parkingLot.calculate(parkingSpace)).isEqualTo((10000));
        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusDays(2));
        assertThat(parkingLot.calculate(parkingSpace)).isEqualTo((20000));
    }
    @DisplayName("차의 주차요금 변경")
    @Test
    void changed_parking_fee() {
        String carNumber = "가나다1234";
        String parkingCode = "A-1";
        String userId = "최한성";
        long moneyAmount = 0;
        Money money = new Money(moneyAmount);
        User user = new User(userId,money);
        Car car = new Car(carNumber);
        ParkingSpace parkingSpace = parkingLot.enterCar(parkingCode, car, LocalDateTime.now());
        parkingLot.calculate(parkingSpace);

        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(29));
        assertThat(parkingLot.changedCalculate(parkingSpace)).isEqualTo((0));

        // 30분 주차
        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(30));
        assertThat(parkingLot.changedCalculate(parkingSpace)).isEqualTo((0));

        // 31분 주차
        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(31));
        assertThat(parkingLot.changedCalculate(parkingSpace)).isEqualTo((1000));

        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusMinutes(61));
        assertThat(parkingLot.changedCalculate(parkingSpace)).isEqualTo((1500));

        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusDays(1));
        assertThat(parkingLot.changedCalculate(parkingSpace)).isEqualTo((15000));

        parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now().plusDays(2));
        assertThat(parkingLot.changedCalculate(parkingSpace)).isEqualTo((30000));

    }
}

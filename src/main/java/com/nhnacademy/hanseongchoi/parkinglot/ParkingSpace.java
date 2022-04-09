package com.nhnacademy.hanseongchoi.parkinglot;

import com.nhnacademy.hanseongchoi.Car;
import java.time.LocalDateTime;

public class ParkingSpace {
    private String code;
    private Car car;
    private LocalDateTime localDateTime;

    public ParkingSpace(String code, Car car, LocalDateTime localDateTime) {
        this.code = code;
        this.car = car;
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    public String getCode() {
        return code;
    }
    public Car getCar() {
        return car;
    }
}

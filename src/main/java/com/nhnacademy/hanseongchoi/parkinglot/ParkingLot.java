package com.nhnacademy.hanseongchoi.parkinglot;

import com.nhnacademy.hanseongchoi.Car;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLot implements ParkingSystem {
    List<ParkingSpace> spaceList = new ArrayList<ParkingSpace>(); // PS 만들 예정


    public ParkingSpace enterCar(String parkingCode, Car car, LocalDateTime localDateTime) {
        ParkingSpace parkingSpace = new ParkingSpace(parkingCode, car, localDateTime);
        spaceList.add(parkingSpace);
        return parkingSpace;
    }


    public String findByCode() {
        return spaceList.get(0).getCode();

    }

    public ParkingSpace getParkingSpace() {
        return spaceList.get(0);
    }

    public Car outCar(String parkingCode, Car car) {
        ParkingSpace parkingSpace = new ParkingSpace(parkingCode, car, LocalDateTime.now());
        spaceList.remove(parkingSpace);
        return car;
    }

    @Override
    public String scanCarInfo() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public long calculate(ParkingSpace parkingSpace) {
        long parkingTime =
            Math.abs(Duration.between(parkingSpace.getLocalDateTime(), LocalDateTime.now()).toSeconds());
        long parkingTimeMinute = parkingTime / 60;
        long parkingFee = 0;
        if (parkingTimeMinute >= 1440) {
            long dayCount = (int) (parkingTimeMinute / 1440);
            parkingFee = 10000 * dayCount;
           return parkingFee;
        }

        if (parkingTimeMinute <= 30) {
            parkingFee = 1000;
            return parkingFee;

        } else {
//          주차된 시간 - 30
            parkingTimeMinute -= 30;
            parkingTimeMinute = (long) Math.ceil((parkingTimeMinute) / 10.0);
            parkingFee = (long) ((parkingTimeMinute * 500) + 1000);
            parkingFee = maximumAmountPerDay(parkingFee);
            return parkingFee;
        }
    }
        public long maximumAmountPerDay(long parkingFee) {
            if (parkingFee > 10000) {
                parkingFee = 10000;
                return parkingFee;
            }
            return parkingFee;
        }

    public long changedCalculate(ParkingSpace parkingSpace) {
        long parkingTime =
            Math.abs(Duration.between(parkingSpace.getLocalDateTime(), LocalDateTime.now()).toSeconds());
        long parkingTimeMinute = parkingTime / 60;
        long parkingFee = 0;
        if (parkingTimeMinute >= 1440) {
            long dayCount = (int) (parkingTimeMinute / 1440);
            parkingFee = 15000 * dayCount;
            return parkingFee;
        }

        if (parkingTimeMinute <= 30) {
            parkingFee = 0;
            return parkingFee;

        } else if (parkingTimeMinute > 30 && parkingTimeMinute <= 60) {
            parkingFee = 1000;
            return  parkingFee;
        }
        else {
            parkingTimeMinute -= 60;
            parkingTimeMinute = (long) Math.ceil((parkingTimeMinute) / 10.0);
            parkingFee = (long) ((parkingTimeMinute * 500) + 1000);
            parkingFee = changedMaximumAmountPerDay(parkingFee);
            return parkingFee;
        }
    }
    public long changedMaximumAmountPerDay(long parkingFee) {
        if (parkingFee > 15000) {
            parkingFee = 15000;
            return parkingFee;
        }
        return parkingFee;
    }
}

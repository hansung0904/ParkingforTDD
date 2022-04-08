package com.nhnacademy.hanseongchoi;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    List<ParkingSpace> spaceList = new ArrayList<ParkingSpace>(); // PS 만들 예정


    public Car enterCar(String parkingCode, Car car) {
        ParkingSpace parkingSpace = new ParkingSpace(parkingCode, car);
        spaceList.add(parkingSpace);
        return car;
    }


    public String findByCode() {
        return spaceList.get(0).getCode();

    }

    public ParkingSpace getParkingSpace() {
        return spaceList.get(0);
    }

    public Car outCar(String parkingCode, Car car) {
        ParkingSpace parkingSpace = new ParkingSpace(parkingCode, car);
        spaceList.remove(parkingSpace);
        return car;
    }
}

package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Device;
import fiveman.hotelservice.response.CustomResponseObject;

public interface DeviceService {
    List<Device> getDevices();

    Device getDevice(long id);

    CustomResponseObject saveDevice(Device device);

    CustomResponseObject updateDevice(Device device);

    CustomResponseObject deleteDevice(long id);
}

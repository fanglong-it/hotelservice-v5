package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Device;

public interface DeviceService {
    List<Device> getDevices();

    Device getDevice(long id);

    Device saveDevice(Device device);

    Device updateDevice(Device device);

    Device deleteDevice(long id);
}

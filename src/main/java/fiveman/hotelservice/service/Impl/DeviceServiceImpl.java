package fiveman.hotelservice.service.Impl;

import java.util.List;
import fiveman.hotelservice.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import fiveman.hotelservice.entities.Device;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.DeviceRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.DeviceService;
import fiveman.hotelservice.utils.Common;

@Service
public class DeviceServiceImpl implements DeviceService {
	Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

	@Autowired
	DeviceRepository deviceRepository;

	@Override
	public List<Device> getDevices() {
		logger.info("GET ALL DEVICES");
		return deviceRepository.findAll();
	}

	@Override
	public Device getDevice(long id) {
        logger.info("START GET DEVICE BY ID");
        if (!deviceRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
        }
        logger.info("START GET DEVICE BY ID");
        return deviceRepository.getDeviceById(id);
    }


    @Override
    public CustomResponseObject saveDevice(Device device) {
        logger.info("START SAVE DEVICE");
        if (deviceRepository.existsById(device.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + device.getId()));
        }
        deviceRepository.save(device);
        logger.info("END SAVE DEVICE");
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Device Success!");
    }

    @Override
    public CustomResponseObject updateDevice(Device device) {
        logger.info("START UPDATE DEVICE");

        if (deviceRepository.existsById(device.getId())) {
            Device oldDevice = deviceRepository.getDeviceById(device.getId());
            if (Utilities.isEmptyString(device.getBrand())) {
                device.setBrand(oldDevice.getBrand());
            }
            if (Utilities.isEmptyString(device.getDescription())) {
                device.setDescription(oldDevice.getDescription());
            }
            if (Utilities.isEmptyString(device.getPartNumber())) {
                device.setPartNumber(oldDevice.getPartNumber());
            }
            if (Utilities.isEmptyString(device.getSerialNo())) {
                device.setSerialNo(oldDevice.getSerialNo());
            }
            if (Utilities.isEmptyString(device.getName())) {
                device.setName(oldDevice.getName());
            }
            deviceRepository.save(device);
            logger.info("END UPDATE DEVICE");
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + device.getId()));

    }

    @Override
    public CustomResponseObject deleteDevice(long id) {
        if (deviceRepository.existsById(id)) {
            logger.info("DELETE DEVICE");
            deviceRepository.deleteById(id);
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
    }
}

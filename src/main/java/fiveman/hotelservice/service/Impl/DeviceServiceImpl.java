package fiveman.hotelservice.service.Impl;

import java.util.List;
import java.util.Optional;

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
		Optional<Device> device = Optional.ofNullable(deviceRepository.findById(id).orElse(null));
		if (device != null) {
			Device dto = device.orElseGet(null);
			if (dto == null) {
				throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not Found Device"));
			}
			logger.info("START GET DEVICE BY ID");
			return dto;
		}
		logger.info("START GET DEVICE BY ID");
		return null;
	}

	@Override
	public boolean addDevice(Device device) { // waiting to support
		return false;
	}

	@Override
	public boolean deleteDevice(long id) {
		logger.info("START DELETE DEVICE BY ID");
		Optional<Device> device = Optional.ofNullable(deviceRepository.findById(id).orElse(null));
		if (device.isPresent()) {
			Device dto = device.orElseGet(null);
			if (dto != null) {
				deviceRepository.deleteById(id);
				logger.info("END GET DEVICE BY ID");
				return true;
			}
		}
		logger.info("END GET DEVICE BY ID");
		return false;
	}
}

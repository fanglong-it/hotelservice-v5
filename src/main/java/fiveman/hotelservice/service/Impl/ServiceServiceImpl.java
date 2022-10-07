package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.ServiceRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.ServiceService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public List<fiveman.hotelservice.entities.Service> getAllServices() {
        log.info("START OF GET ALL SERVICES!!!");
        return serviceRepository.findAll();
    }

    @Override
    public fiveman.hotelservice.entities.Service getServiceById(Long id) {
        log.info("START OF GET SERVICE BY ID");
        if (serviceRepository.existsById(id)) {
            return serviceRepository.getServiceById(id);
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(),
                new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found service by id = " + id));
    }

    fiveman.hotelservice.entities.Service checkService(fiveman.hotelservice.entities.Service service, String method) {
        if (method.equals("save")) {
            if (Utilities.isEmptyString(service.getName())) {
                service.setName(Common.SERVICE_NAME);
            }
            if (Utilities.isEmptyString(service.getPicture())) {
                service.setPicture(Common.SERVICE_PICTURE);
            }
            if (Utilities.isEmptyString(service.getDescription())) {
                service.setDescription(Common.SERVICE_DESCRIPTION);
            }
        } else {
            fiveman.hotelservice.entities.Service oldService = serviceRepository.getServiceById(service.getId());
            if (Utilities.isEmptyString(service.getName())) {
                service.setName(oldService.getName());
            }
            if (Utilities.isEmptyString(service.getPicture())) {
                service.setPicture(oldService.getPicture());
            }
            if (Utilities.isEmptyString(service.getDescription())) {
                service.setDescription(oldService.getDescription());
            }
        }
        return service;
    }

    @Override
    public fiveman.hotelservice.entities.Service updateService(fiveman.hotelservice.entities.Service service) {
        log.info("CHECKING ID FOR UPDATE SERVICE");
        if (serviceRepository.existsById(service.getId())) {
            log.info("ID IS EXIST START OF UPDATE SERVICE");
            service = checkService(service, "update");
            serviceRepository.save(service);
            return serviceRepository.getServiceById(service.getId());
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(HttpStatus.NOT_FOUND.toString(),
                "Not found service by id = " + service.getId()));
    }

    @Override
    public String deleteService(Long id) {
        log.info("CHECKING ID FOR DELETE SERVICE");
        if (serviceRepository.existsById(id)) {
            log.info("START OF DELETE SERVICE BY ID");
            serviceRepository.delete(serviceRepository.getServiceById(id));
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(),
                new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found service by id = " + id));
    }

    @Override
    public fiveman.hotelservice.entities.Service saveServices(fiveman.hotelservice.entities.Service service) {
        if (!serviceRepository.existsById(service.getId())) {
            service = checkService(service, "save");
            serviceRepository.save(service);
        }
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Is exist service id =" + service.getId()));
    }

}

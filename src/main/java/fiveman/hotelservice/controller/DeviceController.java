package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Device;
import fiveman.hotelservice.request.DeviceRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.DeviceService;
import io.swagger.annotations.Api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@Api(tags = "Device")
@RequestMapping("/api/v1")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/devices")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<List<Device>> getDevices() {
        return new ResponseEntity<>(deviceService.getDevices(), HttpStatus.OK);
    }

    @GetMapping("/device/{id}")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<Device> getDevice(@PathVariable("id") long id) {
        return new ResponseEntity<Device>(deviceService.getDevice(id), HttpStatus.OK);
    }

    @PostMapping("/device")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<Device> saveDevice(@RequestBody @Valid DeviceRequest request) {
        Device device = modelMapper.map(request, Device.class);
        return new ResponseEntity<>(deviceService.saveDevice(device), HttpStatus.OK);
    }

    @PutMapping("/device")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<Device> updateDevice(@RequestBody @Valid DeviceRequest request) {
        Device device = modelMapper.map(request, Device.class);
        return new ResponseEntity<>(deviceService.updateDevice(device), HttpStatus.OK);
    }

    @DeleteMapping("/device/{id}")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<Device> deleteDevice(@PathVariable long id) {
        return new ResponseEntity<>(deviceService.deleteDevice(id), HttpStatus.OK);
    }
}

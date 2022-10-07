package fiveman.hotelservice.controller;


import fiveman.hotelservice.entities.Device;
import fiveman.hotelservice.service.DeviceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Api(tags = "device")
@RequestMapping("/api/v1/")
public class DeviceController {


    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getDevices(){
    	return new ResponseEntity<List<Device>>(deviceService.getDevices(), HttpStatus.OK);	
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable("id") long id){
    	return new ResponseEntity<Device>(deviceService.getDevice(id), HttpStatus.OK);	
    }

    @PostMapping("/device")
    public ResponseEntity<Device> insertDevice(@RequestBody Device device){
    	boolean result = deviceService.addDevice(device);
    	if(!result) {
    		return null;
    	}
    	return new ResponseEntity<Device>(device, HttpStatus.OK);
    }

    @DeleteMapping("/device/{id}")
    public Boolean deleteDevice(@PathVariable("id") long id){
        return deviceService.deleteDevice(id);
    }
}

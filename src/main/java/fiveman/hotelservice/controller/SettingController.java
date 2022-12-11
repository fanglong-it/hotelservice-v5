package fiveman.hotelservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.SettingService;
import fiveman.hotelservice.xmlservice.Setting;
import io.micrometer.core.ipc.http.HttpSender.Response;
import io.swagger.annotations.Api;

@RestController
@Api(tags = {"Settings"})
@RequestMapping("/api/v1")
public class SettingController {
    

    @Autowired
    SettingService settingService;


    @GetMapping("/setting")
    public ResponseEntity<Setting> getSetting(){
        return new ResponseEntity<>(settingService.getSettings(), HttpStatus.OK);
    }

    @PostMapping("/setting")
    public ResponseEntity<CustomResponseObject> updateSetting(@RequestBody Setting setting){
        return new ResponseEntity<CustomResponseObject>(settingService.updateSetting(setting), HttpStatus.OK);
        
    }


}

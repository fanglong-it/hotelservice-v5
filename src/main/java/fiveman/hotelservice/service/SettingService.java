package fiveman.hotelservice.service;

import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.xmlservice.Setting;

public interface SettingService {
    Setting getSettings();
    CustomResponseObject updateSetting(Setting setting);
    
}

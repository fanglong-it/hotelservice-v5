package fiveman.hotelservice.service.Impl;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.SettingService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.xmlservice.Setting;
import fiveman.hotelservice.xmlservice.SettingsDAO;

@Service
public class SettingServiceImpl implements SettingService {

    private SettingsDAO settingsDAO = new SettingsDAO();

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public Setting getSettings() {
        Setting s = null;
        try {
            
            InputStream is = new ClassPathResource("file/settings.xml").getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF8");
            s = settingsDAO.getSettingFromUnmarshaller(inputStreamReader);
            // System.out.println(file.getAbsolutePath());
        } catch (Exception e) {
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                    new CustomResponseObject(Common.GET_FAIL, "Can't get the content! msg = " + e.getMessage()));
        }
        return s;
    }

    @Override
    public CustomResponseObject updateSetting(Setting setting) {
        try {
            settingsDAO.updateSettingFromMarshaller(setting);
        } catch (Exception e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.UPDATE_FAIL, "Can't update the content!" + e.getMessage()));
        }
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
    }

}

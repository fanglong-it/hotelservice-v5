package fiveman.hotelservice.service.Impl;

import java.io.File;
import java.io.InputStream;

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
            // getClass().getResource("jsonschema.json").getFile()

            // ClassPathResource res = new ClassPathResource("file/settings.xml");
            // File file = new File(res.getPath());

            File file = new ClassPathResource("file/settings.xml").getFile();

            System.out.println(file.getPath());
            // File file = ResourceUtils.getFile("classpath:file/settings.xml");
            // System.out.println("" + file.getPath());
            s = settingsDAO.getSettingFromUnmarshaller(file.getPath());
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
            File file = ResourceUtils.getFile("classpath:file/settings.xml");
            settingsDAO.updateSettingFromMarshaller(file.toPath().toString(), setting);
        } catch (Exception e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.UPDATE_FAIL, "Can't update the content!"));
        }
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
    }

}

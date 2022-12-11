package fiveman.hotelservice.xmlservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class SettingsDAO {

    public Setting getSettingFromUnmarshaller(InputStreamReader inputStreamReader) throws JAXBException {
        // File xmlFile = new File(file);
        // JAXBContext jaxbContext;
        // Setting s = null;
        // jaxbContext = JAXBContext.newInstance(Setting.class);
        // Unmarshaller u = jaxbContext.createUnmarshaller();
        // s = (Setting) u.unmarshal(xmlFile);
        // return s;

        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmarshaller;
        Setting s = null;
        try {
            jaxbContext = JAXBContext.newInstance(Setting.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            s = (Setting) jaxbUnmarshaller.unmarshal(inputStreamReader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Setting updateSettingFromMarshaller(String file, Setting setting) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Setting.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(setting, new File(file));
        return setting;
    }

}

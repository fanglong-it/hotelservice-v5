package fiveman.hotelservice.xmlservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;

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

    public Setting updateSettingFromMarshaller(Setting setting) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(Setting.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new ClassPathResource("file/settings.xml").getFile();
        try {
            m.marshal(setting, file);
        } finally {
        }

        return setting;

        // Marshaller marshaller = jaxbContext.createMarshaller();
        // OutputStream outputStream = new FileOutputStream("output.xml");
        // try {
        // marshaller.marshal(address, outputStream);
        // } finally {
        // outputStream.close();
        // }
    }

}

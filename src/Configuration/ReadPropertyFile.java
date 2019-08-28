package Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

    private Properties prop;
    private InputStream input;

    public ReadPropertyFile(String filepath) {
        System.out.print(filepath);
        prop = new Properties();
        input = ReadPropertyFile.class.getClassLoader().getResourceAsStream(filepath);
        try{
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProp(String aprop) {
        return prop.getProperty(aprop);
    }

}

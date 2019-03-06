package org.tilacyn.news.wget.lenta;


import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.tilacyn.news.wget.Wget;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Component(immediate = true)
@Service
@Property(name = "source", value = "lenta")
public class LentaWget implements Wget {
    public List<String> getNewsTitles() {
        URL url;
        try {
            url = new URL("https://api.lenta.ru/lists/latest");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Error occurred in https://api.lenta.ru/lists/latest request");
            return null;
        }

        InputStreamReader input;

        try {
            input = new InputStreamReader(url.openStream());
        } catch (IOException e) {
            System.out.println("Error opening stream in lenta news provider");
            e.printStackTrace();
            return null;
        }

        LentaParser parser = new LentaParser(input);

        try {
            return parser.getTitles();
        } catch (IOException e) {
            System.out.println("Parsing lenta news failed");
            e.printStackTrace();
        }
        return null;
    }
}

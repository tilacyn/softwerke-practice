package org.tilacyn.news.wget.rss;

import org.osgi.service.component.annotations.Component;
import org.tilacyn.news.wget.Wget;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Component(immediate = true)
public class RSSWget implements Wget {

    public List<String> getNewsTitles() {
        URL url;
        try {
            url = new URL("http://www.aif.ru/rss/news.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Error in http://www.aif.ru/rss/news.php request");
            return null;
        }

        InputStreamReader input;

        try {
            input = new InputStreamReader(url.openStream());
        } catch (IOException e) {
            System.out.println("Error opening stream in rss news provider");
            e.printStackTrace();
            return null;
        }

        RSSParser parser = new RSSParser(input);

        try {
            return parser.getTitles();
        } catch (IOException e) {
            System.out.println("Parsing rss news failed");
            e.printStackTrace();
        }

        return null;
    }

    public String getSourceName() {
        return "rss";
    }
}

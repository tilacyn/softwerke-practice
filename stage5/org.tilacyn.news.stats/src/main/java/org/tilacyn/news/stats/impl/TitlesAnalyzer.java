package org.tilacyn.news.stats.impl;

import org.apache.felix.scr.annotations.*;
import org.apache.felix.scr.annotations.Properties;
import org.tilacyn.news.stats.StatisticsProvider;
import org.tilacyn.news.wget.Wget;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Properties({
        @Property(name = "osgi.command.scope", value = "news"),
        @Property(name = "osgi.command.function", value = {"stats" })
})
@Service
public class TitlesAnalyzer implements StatisticsProvider {
    @Reference(bind = "bindLenta", target = "(source=lenta)", policy = ReferencePolicy.DYNAMIC)
    private volatile Wget lentaWget;

    @Reference(bind = "bindRSS", target = "(source=rss)", policy = ReferencePolicy.DYNAMIC)
    private volatile Wget RSSWget;

    public TitlesAnalyzer() {
    }

    protected void bindLenta(Wget wget) {
        lentaWget = wget;
    }

    protected void bindRSS(Wget wget) {
        RSSWget = wget;
    }

    private void suggestAvailableServices() {
        if (lentaWget == null && RSSWget == null) {
            System.out.println("No news providers registered");
            return;
        }
        System.out.println("Try");
        if (RSSWget != null) {
            System.out.println("news:stats rss");
        }
        if (lentaWget != null) {
            System.out.println("news:stats lenta");
        }
    }

    public void stats() {
        System.out.println("Error, no news source specified");
        suggestAvailableServices();
    }

    public void stats(String src) {
        Wget wget = null;

        if (src.equals("lenta")) {
            wget = lentaWget;
            if (wget == null) {
                System.out.println("Error: Lenta news provider is not currently registered\n");
                suggestAvailableServices();
                return;
            }
        }

        if (src.equals("rss")) {
            wget = RSSWget;
            if (wget == null) {
                System.out.println("Error: RSS news provider is not currently registered\n");
                suggestAvailableServices();
                return;
            }
        }

        if (wget == null) {
            System.out.println("Error: unknown source specified, no such news provider");
            suggestAvailableServices();
            return;
        }

        List<String> list = tenRegularWorlds(wget.getNewsTitles());
        for (String s : list) {
            System.out.println(s);
        }
    }

    private List<String> tenRegularWorlds(List<String> titles) {
        List<String> result = new ArrayList<String>();

        HashMap<Integer, String> words = new HashMap<Integer, String>();
        HashMap<Integer, Integer> entries = new HashMap<Integer, Integer>();

        for (String title : titles) {
            String[] localWords = title.split(" ");
            for (String word : localWords) {
                words.put(word.hashCode(), word);
                if (entries.containsKey(word.hashCode())) {
                    Integer current = entries.get(word.hashCode());
                    entries.put(word.hashCode(), current + 1);
                } else {
                    entries.put(word.hashCode(), 1);
                }
            }
        }

        List<Map.Entry<Integer, Integer>> tenEntries =
                entries
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparingInt(Map.Entry::getValue))
                        .limit(10)
                        .collect(Collectors.toList());

        for (Map.Entry entry : tenEntries) {
            result.add(words.get(entry.getKey()));
        }

        return result;
    }
}

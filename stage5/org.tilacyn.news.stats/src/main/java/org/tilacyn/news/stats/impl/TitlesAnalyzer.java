package org.tilacyn.news.stats.impl;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.tilacyn.news.stats.StatisticsProvider;
import org.tilacyn.news.wget.Wget;

import java.util.*;
import java.util.stream.Collectors;

@Component(
        property = {
                "osgi.command.scope=news", "osgi.command.function=stats"
        }
)
public class TitlesAnalyzer implements StatisticsProvider {

    @Reference(
            service = Wget.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unbindWget",
            bind = "bindWget"
    )
    private volatile List<Wget> sources = new ArrayList<>();

    public TitlesAnalyzer() {
    }

    protected void bindWget(Wget wget) {
        sources.add(wget);
    }

    protected void unbindWget(Wget wget) {
        sources.remove(wget);
    }

    private void suggestAvailableServices() {
        if (sources.isEmpty()) {
            System.out.println("No news providers currently registered");
            return;
        }
        System.out.println("Try:");
        for (Wget wget : sources) {
            System.out.println("news:stats " + wget.getSourceName());
        }
    }

    public void stats() {
        System.out.println("Error: no news source specified");
        suggestAvailableServices();
    }

    public void stats(String src) {


        Optional<Wget> wget = sources.stream().filter(e -> e.getSourceName().equals(src)).findAny();

        if (wget.isPresent()) {

            List<String> list = tenRegularWorlds(wget.get().getNewsTitles());
            for (String s : list) {
                System.out.println(s);
            }
            return;
        }

        System.out.println("Error: unknown source specified, no such news provider registered");

        suggestAvailableServices();
    }

    private List<String> tenRegularWorlds(List<String> titles) {
        List<String> result = new ArrayList<>();

        HashMap<Integer, String> words = new HashMap<>();
        HashMap<Integer, Integer> entries = new HashMap<>();

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
                        .sorted(Comparator.comparingInt(e -> -e.getValue()))
                        .limit(10)
                        .collect(Collectors.toList());

        for (Map.Entry entry : tenEntries) {
            result.add(words.get(entry.getKey()));
        }

        return result;
    }
    
}

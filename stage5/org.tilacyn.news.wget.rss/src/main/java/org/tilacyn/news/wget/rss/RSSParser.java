package org.tilacyn.news.wget.rss;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class RSSParser {
    private InputStreamReader input;

    RSSParser(InputStreamReader input) {
        this.input = input;
    }

    List<String> getTitles() throws IOException {
        StringBuilder tag = new StringBuilder();
        int cint = 0;
        List<String> titles = new ArrayList<String>();

        boolean inTag = false;
        boolean inTitle = false;
        StringBuilder title = new StringBuilder();

        while (cint != -1) {
            cint = input.read();
            char c = (char) cint;
            //System.out.print(c);

            if (inTitle) {
                title.append(c);
            }

            if (c == '<') {
                inTag = true;
                continue;
            }

            if (c == '>') {
                inTag = false;
                if (tag.toString().equals("title")) {
                    inTitle = true;
                }
                if (tag.toString().equals("/title")) {
                    inTitle = false;
                    titles.add(title.substring(0, title.length() - 8));
                    title = new StringBuilder();
                }

                tag = new StringBuilder();
            }

            if (inTag) {
                tag.append(c);
            }
        }

        for (int i = 0; i < titles.size(); i++) {
            titles.set(i, removeCDATA(titles.get(i)));
        }

        return titles;
    }

    private String removeCDATA(String s) {
        if (s.startsWith("<![CDATA[")) {
            return s.substring(9, s.length() - 3);
        }
        return s;
    }

}

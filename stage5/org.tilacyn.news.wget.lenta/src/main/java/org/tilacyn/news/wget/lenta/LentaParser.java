package org.tilacyn.news.wget.lenta;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LentaParser {
    private InputStreamReader input;

    LentaParser(InputStreamReader input) {
        this.input = input;
    }

    List<String> getTitles() throws IOException {
        StringBuilder tag = new StringBuilder();
        int cint = 0;
        List<String> titles = new ArrayList<String>();

        StringBuilder inputS = new StringBuilder();

        while (cint != -1) {
            cint = input.read();
            inputS.append((char) cint);
        }


        Pattern pattern = Pattern.compile("\"title\":\".+?\"");
        Matcher matcher = pattern.matcher(inputS.toString());

        // Find all matches
        while (matcher.find()) {
            // Get the matching string
            String match = matcher.group();
            titles.add(leaveTitle(match));
        }

        return titles;
    }

    private String leaveTitle(String s) {
        //String result = s.substring()
        return s.substring(9, s.length() - 1);
    }

}

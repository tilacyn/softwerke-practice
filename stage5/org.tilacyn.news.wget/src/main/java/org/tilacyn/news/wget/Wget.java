package org.tilacyn.news.wget;

import java.util.List;

/**
 * this interface implementation is expected to download news API
 * and extract news titles
 */
public interface Wget {
    /**
     * it gets news API and returns list of news titles
     * @return list of news titles
     */
    List<String> getNewsTitles();

    /**
     * returns name of news source
     * @return name of news source
     */
    String getSourceName();
}

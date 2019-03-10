package org.tilacyn.news.stats;

/**
 * this interface implementation is expected to provide
 * statistics on any topic. It just should be printed to stdout when 'stats' method is called
 */
public interface StatisticsProvider {
    /**
     * this method should print statistics to stdout
     * obtained from specified source
     * @param s specified source
     */
    void stats(String s);

    /**
     * this method should print available statistics sources
     */
    void stats();
}

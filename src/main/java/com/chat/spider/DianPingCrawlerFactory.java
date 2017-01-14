package com.chat.spider;

import com.chat.datamodel.dao.DianPingDao;
import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by xiangtianyu on 2017/1/14.
 */
public class DianPingCrawlerFactory implements CrawlController.WebCrawlerFactory<DianPingCrawler> {
    private DianPingDao dianPingDao;

    public DianPingCrawlerFactory(DianPingDao dianPingDao) {
        this.dianPingDao = dianPingDao;
    }

    public DianPingCrawler newInstance() throws Exception {
        return new DianPingCrawler(dianPingDao);
    }
}

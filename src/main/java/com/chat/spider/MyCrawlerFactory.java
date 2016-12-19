package com.chat.spider;

import com.chat.datamodel.dao.ChapterDao;
import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by xiangtianyu on 2016/12/19.
 */
public class MyCrawlerFactory implements CrawlController.WebCrawlerFactory<MyCrawler> {

    private ChapterDao chapterDao;

    public MyCrawlerFactory(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }

    public MyCrawler newInstance() throws Exception {
        return new MyCrawler(chapterDao);
    }
}

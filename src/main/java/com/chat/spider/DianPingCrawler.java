package com.chat.spider;

import com.chat.datamodel.dao.DianPingDao;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.springframework.context.annotation.Configuration;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.aspectj.lang.annotation.Aspect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by xiangtianyu on 2017/1/14.
 */
@Configuration
public class DianPingCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    private int hrefCount = 0;

    private DianPingDao dianPingDao;

    public DianPingCrawler(DianPingDao dianPingDao) {
        this.dianPingDao = dianPingDao;
    }

    public DianPingCrawler() {

    }

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://www.dianping.com/search/category/2/10/");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        String cate = url.substring(45);

        hrefCount++;

        System.out.println("cate:" + cate);
        System.out.println("url:" + url);

        if (!cate.startsWith("r"))  return;

        System.out.println("ok:" + 1);


        System.out.println("hrefCount:" + hrefCount);


    }
}

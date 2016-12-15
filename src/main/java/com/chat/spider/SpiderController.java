package com.chat.spider;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiangtianyu on 2016/12/15.
 */
@CrossOrigin
@RestController
public class SpiderController {
    @RequestMapping(value = "/spider", method = RequestMethod.GET)
    public void StartSpider() throws Exception{
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 7;
        int maxDepthOfCrawling = 1;

        List<Header> headerList = Arrays.asList(
                //new BasicHeader("Set-Cookie", "PassportCaptchaId=; Max-Age=0; Path=/; Domain=.zongheng.com; HttpOnly"),
                //new BasicHeader("Set-Cookie", "logon=ODIzMDI2MA%3D%3D%0D%0A%7CMA%3D%3D%0D%0A%7CdGpqeHR5MTIz%0D%0A%7CdGpqeHR5%0D%0A%7CZmFsc2U%3D%0D%0A%7CLTE5ODAwNjgxMjI%3D%0D%0A%7C8161BD0C67AA7C6A6D444135E3338001; domain=.zongheng.com; path=/; expires=Sat, 25-Mar-2017 06:24:30 GMT"),
                //new BasicHeader("Set-Cookie", "channel=zh; domain=.zongheng.com; path=/; expires=Sat, 25-Mar-2017 06:24:30 GMT"),
                //new BasicHeader("Set-Cookie", "ZHID=1481782998491ZHG1TOX0PT2L41M7K01; domain=.zongheng.com; path=/"),
                //new BasicHeader("Set-Cookie", "visit_list=8230260; domain=.zongheng.com; path=/"),
                //new BasicHeader("Set-Cookie", "zh_visitTime=1481782998520; domain=.zongheng.com; path=/"),
                //new BasicHeader("Set-Cookie", "v_user=http%3A%2F%2Fbook.zongheng.com%2Fshowchapter%2F189169.html%7Chttp%3A%2F%2Fbook.zongheng.com%2Fshowchapter%2F189169.html%7C78613899; domain=.zongheng.com; path=/"),
                //new BasicHeader("Set-Cookie", "__logon__=ODIzMDI2MA%3D%3D%0D%0A%7CMA%3D%3D%0D%0A%7CdGpqeHR5MTIz%0D%0A%7CdGpqeHR5%0D%0A%7CZmFsc2U%3D%0D%0A%7CLTE5ODAwNjgxMjI%3D%0D%0A%7C8161BD0C67AA7C6A6D444135E3338001; Max-Age=8640000; Path=/; Domain=.zongheng.com; HttpOnly"),
                //new BasicHeader("Set-Cookie", "zhffr=book.zongheng.com; domain=.zongheng.com; path=/"),
                new BasicHeader("Cookie", "logon=ODIzMDI2MA%3D%3D%0D%0A%7CMA%3D%3D%0D%0A%7CdGpqeHR5MTIz%0D%0A%7CdGpqeHR5%0D%0A%7CZmFsc2U%3D%0D%0A%7CLTYzMDA4ODY5Nw%3D%3D%0D%0A%7C31C25B61261372904A489A0739F37BC5")
        );

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(maxDepthOfCrawling);
        config.setDefaultHeaders(headerList);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed("http://book.zongheng.com/showchapter/189169.html");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}

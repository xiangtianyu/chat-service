package com.chat.spider;

import com.chat.datamodel.dao.ChapterDao;
import com.chat.datamodel.domain.Chapter;
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
 * Created by xiangtianyu on 2016/12/15.
 */
@Configuration
public class MyCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    private ChapterDao chapterDao;

    public MyCrawler(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }

    public MyCrawler() {

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
                && href.startsWith("http://book.zongheng.com/chapter/189169");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        String index = url.substring(40, url.length()-5);
        int cIndex = Integer.parseInt(index);
        System.out.println("URL: " + url);
        System.out.println("Index: " + index);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            Document doc = Jsoup.parse(html);

            String title = doc.select(".txt").first().child(0).children().last().text();
            Elements body = doc.select("#chapterContent > p");

            File folderPath = new File("/novel");
            if (!folderPath.exists()) {
                folderPath.mkdir();
            }
            try {
                File filePath = new File("/novel/" + title+".txt");
                filePath.createNewFile();
                FileWriter fw = new FileWriter(filePath);
                fw.write(title);
                fw.write("\r\n\r\n");
                for (Element e : body) {
                    fw.write(e.text() + "\r\n\r\n");
                }
                fw.flush();
                fw.close();
            }
            catch (Exception e) {
                System.out.println("新建文件操作出错");
                e.printStackTrace();
            }

            System.out.println("Title: " + title);

            Chapter chapter = new Chapter();
            chapter.setTitle(title);
            chapter.setBookName("雪中悍刀行");
            chapter.setcIndex(cIndex);
            chapterDao.save(chapter);

        }
    }
}

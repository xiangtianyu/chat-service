package com.chat.test.novel;

import com.chat.datamodel.dao.ChapterDao;
import com.chat.datamodel.domain.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Created by xiangtianyu on 2016/12/22.
 */
@Service
public class NovelService {
    @Autowired
    private ChapterDao chapterDao;

    public String integrationChapter() throws IOException{
        List<Chapter> chapters = chapterDao.findChapter();
        File folderPath = new File("/novel/xzhdx");
        String bookName = "雪中悍刀行";
        if (!folderPath.exists()) {
            folderPath.mkdir();
        }
        File filePath = new File("/novel/xzhdx/" + bookName+".txt");
        filePath.createNewFile();
        FileWriter fw = new FileWriter(filePath);
        for (Chapter chapter : chapters) {
            String title = chapter.getTitle();
            File path = new File("/novel/" + title+".txt");
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String tempString;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                fw.write(tempString);
                fw.write("\r\n");
                line++;
            }
            reader.close();
        }
        fw.close();
        return "SUCCESS";
    }
}

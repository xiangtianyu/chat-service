package com.chat.test.novel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by xiangtianyu on 2016/12/22.
 */
@CrossOrigin
@RestController
public class NovelController {
    @Autowired
    private NovelService novelService;

    @ResponseBody
    @RequestMapping(value = "/novel/integration", method = RequestMethod.GET)
    public String integrationChapter() throws IOException{
        return novelService.integrationChapter();
    }
}

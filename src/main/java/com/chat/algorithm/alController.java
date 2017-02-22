package com.chat.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiangtianyu on 2017/2/21.
 */
@CrossOrigin
@RestController
public class alController {
    @Autowired
    private alService alService;

    @RequestMapping(value = "/algorithm/testBST", method = RequestMethod.GET)
    public @ResponseBody void testBST() throws Exception {
        alService.testBST();
    }
}

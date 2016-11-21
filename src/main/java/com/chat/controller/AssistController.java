package com.chat.controller;

/**
 * Created by xiangtianyu on 2016/11/21.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.text.ParseException;

import com.chat.assist.WhiteList;
import com.chat.datamodel.dto.UserDTO;
import com.chat.datamodel.dto.WhiteListDTO;
import com.chat.service.AssistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class AssistController extends BaseController {

    @Autowired
    private AssistService assistService;

    @WhiteList()
    @RequestMapping(value = "/whitelist/findall", method = RequestMethod.GET)
    public @ResponseBody List<WhiteListDTO> getAllWL(HttpServletRequest request) {
        return assistService.getAllWL();
    }

}

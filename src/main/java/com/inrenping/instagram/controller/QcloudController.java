package com.inrenping.instagram.controller;

import com.alibaba.fastjson.JSON;
import com.inrenping.instagram.entity.qcloud.InstagramQuery;
import com.inrenping.instagram.entity.vo.R;
import com.inrenping.instagram.service.impl.QcloudService;
import com.inrenping.instagram.utils.RUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("qcloud")
public class QcloudController {

    @Autowired
    QcloudService qcloudService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R page(@RequestParam(name = "userId") Long userId,
                  @RequestParam(name = "pageNo") Integer pageNo,
                  @RequestParam(name = "pageSize") Integer pageSize) {
        try{
            pageSize = pageSize > 30 ? 30 : pageSize;
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            Page<InstagramQuery> list = qcloudService.pageByUserId(userId, pageable);
            return RUtil.success(list);
        }catch(Exception ex){
            return RUtil.error(1,ex.getMessage());
        }

    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R detail(@RequestParam(name = "id") Long id) {
        try{
            InstagramQuery instagramQuery = qcloudService.detail(id);
            return RUtil.success(instagramQuery);
        }catch(Exception ex){
            return RUtil.error(1,ex.getMessage());
        }
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    @CrossOrigin(value = "*")
    public byte[] image(@RequestParam(name = "id") Long id) throws Exception {
        File file = qcloudService.getFile(id);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}

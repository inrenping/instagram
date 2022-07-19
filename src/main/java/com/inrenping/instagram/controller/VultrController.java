package com.inrenping.instagram.controller;

import com.inrenping.instagram.entity.vo.R;
import com.inrenping.instagram.service.IVultrService;
import com.inrenping.instagram.utils.RUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("vultr")
public class VultrController {

    @Autowired
    IVultrService vultrService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R hello(@RequestParam(name = "value") String value) {
        return RUtil.success(value);
    }

    @RequestMapping(value = "/firstfetchquery", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R firstFetchQuery(@RequestParam(name = "id") String id) {
        try {
            String result = vultrService.firstFetch(id);
            return RUtil.success(result);
        } catch (Exception ex) {
            return RUtil.error(1,ex.getMessage());
        }
    }

    @RequestMapping(value = "/firstfetchquerywithupdate", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R firstFetchQueryWithUpdate(@RequestParam(name = "id") String id) {
        try {
            String result= vultrService.firstFetchWithUpdate(id);
            return RUtil.success(result);
        } catch (Exception ex) {
            return RUtil.error(1,ex.getMessage());
        }
    }

    @RequestMapping(value = "/secondfetchquery", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R secondFetchQuery(@RequestParam(name = "end_cursor") String end_cursor) {
        try {
            String result = vultrService.secondFetch(end_cursor);
            return RUtil.success(result);
        } catch (Exception ex) {
            return RUtil.error(1,ex.getMessage());
        }
    }

    @RequestMapping(value = "/secondfetchquerywithupdate", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R secondFetchQueryWithUpdate(@RequestParam(name = "end_cursor") String end_cursor) {
        try {
            String result= vultrService.secondFetchWithUpdate(end_cursor);
            return RUtil.success(result);
        } catch (Exception ex) {
            return RUtil.error(1,ex.getMessage());
        }
    }

    @RequestMapping(value = "/thirdfetchquery", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R thirdFetchQuery(@RequestParam(name = "shortcode") String shortcode){
        try {
            String result= vultrService.thirdFetch(shortcode);
            return RUtil.success(result);
        } catch (Exception ex) {
            return RUtil.error(1,ex.getMessage());
        }
    }

    @RequestMapping(value = "/recursionfetchquerywithupdate", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R recursionFetchQueryWithUpdate(@RequestParam(name = "end_cursor") String end_cursor) {
        try {
            String result= vultrService.recursionFetchQueryWithUpdate(end_cursor);
            return RUtil.success(result);
        } catch (Exception ex) {
            return RUtil.error(1,ex.getMessage());
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public R download() {
        try {
            String result = vultrService.download();
            return RUtil.success(result);
        } catch (Exception ex) {
            return RUtil.error(1,ex.getMessage());
        }
    }

}

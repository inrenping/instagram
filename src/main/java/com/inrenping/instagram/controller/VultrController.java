package com.inrenping.instagram.controller;

import com.inrenping.instagram.service.IVultrService;
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
    public String hello(@RequestParam(name = "value") String value) {
        return value;
    }

    @RequestMapping(value = "/firstfetchquery", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public String firstFetchQuery() {
        try {
            String result = vultrService.firstFetch();
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping(value = "/firstfetchquerywithupdate", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public String firstFetchQueryWithUpdate() {
        try {
            String result= vultrService.firstFetchWithUpdate();
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    /**
     *
     * @param end_cursor
     * @return
     */
    @RequestMapping(value = "/secondfetchquery", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public String secondFetchQuery(@RequestParam(name = "end_cursor") String end_cursor) {
        try {
            String result = vultrService.secondFetch(end_cursor);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    /**
     * 循环调用
     * @param end_cursor
     * @return
     */
    @RequestMapping(value = "/recursionfetchquerywithupdate", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public String recursionFetchQueryWithUpdate(@RequestParam(name = "end_cursor") String end_cursor) {
        try {
            String result= vultrService.recursionFetchQueryWithUpdate(end_cursor);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping(value = "/secondfetchquerywithupdate", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public String secondFetchQueryWithUpdate(@RequestParam(name = "end_cursor") String end_cursor) {
        try {
            String result= vultrService.secondFetchWithUpdate(end_cursor);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(value = "*")
    public String download() {
        try {
            String result = vultrService.download();
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

}

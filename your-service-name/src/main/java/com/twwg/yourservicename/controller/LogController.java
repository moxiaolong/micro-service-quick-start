package com.twwg.yourservicename.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * 日志控制器
 *
 * @author dragon
 * @date 2021/09/03
 */
@Api("日志控制器")
@RestController
@RequestMapping("/log")
public class LogController {

    String fileNameReg = "^[0-9A-Za-z._]*$";


    @GetMapping("list")
    public String getLogList() {
        return doCommand("ls", "logs");
    }

    @GetMapping
    @ApiOperation("定位日志")
    public String getLogList(@RequestParam @ApiParam("文件名") String fileName, @RequestParam @ApiParam("过滤内容") String content) {
        if (!Pattern.matches(fileNameReg, fileName)) {
            throw new IllegalArgumentException("文件名非法，请检查是否包含特殊字符。");
        }
        return doCommand("/bin/sh", "-c", "cat logs/web_info.log |grep " + "'" + content + "'");
    }


    public String doCommand(String... cmd) {
        try {
            Process ps = Runtime.getRuntime().exec(cmd);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}

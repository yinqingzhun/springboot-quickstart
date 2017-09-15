package com.yqz.springboot.quickstart.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yqz.springboot.quickstart.model.ReturnValue;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by yinqingzhun on 2017/09/15.
 */
public class SohuVideoDownloadService {
    private final Logger logger = LoggerFactory.getLogger(SohuVideoDownloadService.class);
    private final String apiUrlBase = "http://agv.api.autohome.com.cn/mc_qq/c100/s100/api/video/";
    private final String dir = "/data/video_download/sohu/";
    private String workDir = "";

    public static void main(String[] args) {
        SohuVideoDownloadService sohuVideoDownloadService = new SohuVideoDownloadService();
        String downloadUrl = sohuVideoDownloadService.getVideoDownloadUrl("http://m.tv.sohu.com/pl/9390897/92472524.shtml?channeled=1211020002", "92472524");
        System.out.println(downloadUrl);
    }

    public SohuVideoDownloadService() {
        workDir = System.getProperty("user.dir");
        logger.debug(workDir);
    }

    private String createGuid() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("nashorn");
        try {
            HashMap<String, String> map = new HashMap<>();

            scriptEngine.eval("function createUUID() {\n" +
                    "            var t = Math.floor(400)\n" +
                    "              , e = Math.floor(672)\n" +
                    "              , i = Math.floor(Math.sqrt(t * t + e * e)) || 0\n" +
                    "              , o = Math.round(5) || 1;\n" +
                    "            return  parseFloat(1e3 * +new Date + o + i + Math.round(1e3 * Math.random())).toString();  \n" +
                    "        };var uuid=createUUID();");


            return scriptEngine.get("uuid").toString();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getVideoDownloadUrl(String pageUrl, String vid) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            HashMap<String, String> headerMap = new HashMap<>();
            headerMap.put("Accept", "*/*");
            headerMap.put("Accept-Encoding", "gzip, deflate");
            headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
            headerMap.put("Cache-Control", "no-cache");
            headerMap.put("Connection", "keep-alive");
            headerMap.put("Pragma", "no-cache");
            headerMap.put("Referer", pageUrl);
            headerMap.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Mobile Safari/537.36");

            String playInfoUrl = String.format("http://m.tv.sohu.com/phone_playinfo?vid=%s&site=2&appid=tv&api_key=f351515304020cad28c92f70f002261c&plat=17&sver=1.0&partner=1&uid=%s&muid=%s&_c=1&pt=3&qd=680&src=11060001&_=%s", vid, createGuid(), createGuid(), System.currentTimeMillis());
            CloseableHttpClient httpClient = HttpClientBuilder.create().setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Mobile Safari/537.36").build();

            HttpGet httpGet = new HttpGet(playInfoUrl);
            HttpResponse response = httpClient.execute(httpGet);

            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                String body = EntityUtils.toString(response.getEntity(), "GBK");
                JsonNode jsonNode = fromJSON(JsonNode.class, body);
                if (jsonNode != null) {
                    return jsonNode.at("/data/urls/downloadUrl/0").asText();
                }
            }

        } catch (IOException e) {
            logger.warn("获取搜狐视频下载地址失败", e);
        }
        return null;
    }

    public static <T> T fromJSON(final Class<T> clazz, final String jsonPacket) {
        T data = null;
        try {
            data = new ObjectMapper().readValue(jsonPacket, clazz);
        } catch (Exception e) {
        }
        return data;
    }


}

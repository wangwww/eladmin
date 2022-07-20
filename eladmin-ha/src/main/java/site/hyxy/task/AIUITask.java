package site.hyxy.task;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.hyxy.entity.aiui.TTSResult;
import site.hyxy.service.aiui.AiuiService;

import javax.annotation.PostConstruct;

@Slf4j
@Service("aiuiTask")
public class AIUITask {
    @Autowired
    AiuiService aiuiService;

    @PostConstruct
    public void init() {
        log.error("---------- aiui task 初始化");
    }

    public void getWeather() {
        TTSResult ttsResult = null;
        try {
            ttsResult = aiuiService.send("今天天气怎么样");
        } catch (Exception e) {
            log.error("获取天气信息失败");
            return;
        }

        if (ttsResult == null) {
            log.error("未获取到天气信息");
        } else {
            log.info("获取到天气信息：{}", JSONObject.toJSONString(ttsResult));
        }
    }
}

package site.hyxy.task;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.hyxy.config.NodeRedConfig;
import site.hyxy.entity.aiui.TTSResult;
import site.hyxy.service.aiui.AiuiService;
import site.hyxy.utils.RestUtils;

import java.util.Map;

@Slf4j
@Service("aiuiTask")
public class AIUITask {
    @Autowired
    AiuiService aiuiService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NodeRedConfig nodeRedConfig;

    public void getWeather(String location) {
        TTSResult ttsResult = null;
        try {
            ttsResult = aiuiService.send(location + "今天天气怎么样");
        } catch (Exception e) {
            log.error("获取天气信息失败");
            e.printStackTrace();
            return;
        }

        if (ttsResult == null) {
            log.error("未获取到天气信息");
        } else {
            log.info("获取到天气信息：{}", JSONObject.toJSONString(ttsResult));
            String url = nodeRedConfig.getNodeRedUrl("setWeather");
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, RestUtils.buildHttpEntity(ttsResult), Map.class);

            log.info("nodered 返回结果：{}", JSONObject.toJSONString(responseEntity.getBody()));
        }
    }
}

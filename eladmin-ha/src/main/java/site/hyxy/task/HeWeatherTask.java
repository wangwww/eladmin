package site.hyxy.task;

import com.heweather.api.response.weather.HeWeatherWeatherHourlyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.hyxy.config.NodeRedConfig;
import site.hyxy.service.weather.he.HeWeatherService;
import site.hyxy.utils.RestUtils;

import java.util.Map;

@Slf4j
@Service("heWeatherTask")
public class HeWeatherTask {
    @Autowired
    HeWeatherService heWeatherService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    NodeRedConfig nodeRedConfig;

    public void report24HoursWeather() {
        HeWeatherWeatherHourlyResponse weather24H = heWeatherService.getWeather24H();
        String url = nodeRedConfig.getNodeRedUrl("setWeather24h");
        ResponseEntity<Map> responseEntity =
            restTemplate.postForEntity(url, RestUtils.buildHttpEntity(weather24H), Map.class);
        log.info("接收到nodered响应：{}", responseEntity.getBody());
    }
}

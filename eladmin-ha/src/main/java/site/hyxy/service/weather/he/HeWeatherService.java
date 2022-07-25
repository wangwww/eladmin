package site.hyxy.service.weather.he;

import com.alibaba.fastjson.JSONObject;
import com.heweather.api.HeWeatherInitialize;
import com.heweather.api.response.weather.HeWeatherWeatherHourlyResponse;
import com.heweather.api.response.weather.HeWeatherWeatherNowResponse;
import com.heweather.api.service.HeWeatherWeatherService;
import com.heweather.api.service.impl.HeWeatherWeatherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.hyxy.config.HeWeatherConfig;

import javax.annotation.PostConstruct;

/**
 * 使用和风天气SDK集成和风天气：https://github.com/dongjunchen1993/HeWeather_SDK_V7
 * HeWeatherAirService 空气质量相关
 * HeWeatherAstronomyService 太阳和月亮相关
 * HeWeatherHistoricalService 历史数据相关
 * HeWeatherLifestyleService 生活指数相关
 * HeWeatherLocationService 城市、POI 检索相关
 * HeWeatherMinutelyService 分钟降水相关
 * HeWeatherPoiService Poi天气数据 相关
 * HeWeatherWarningService 城市预警信息相关
 * HeWeatherWeatherService 城市天气数据相关
 */
@Slf4j
@Service
public class HeWeatherService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HeWeatherConfig heWeatherConfig;

    @PostConstruct
    public void init() {
        HeWeatherInitialize.init(heWeatherConfig.getId(), heWeatherConfig.getKey(),
            heWeatherConfig.getDomain());
    }

    public HeWeatherWeatherNowResponse getWeatherNow() {
        HeWeatherWeatherService weatherService = new HeWeatherWeatherServiceImpl();
        // 地点固定为西安
        HeWeatherWeatherNowResponse weatherNow = weatherService.getWeatherNow(heWeatherConfig.getLocation());
        log.info(JSONObject.toJSONString(weatherNow));
        return weatherNow;
    }

    public HeWeatherWeatherHourlyResponse getWeather24H() {
        HeWeatherWeatherService weatherService = new HeWeatherWeatherServiceImpl();
        // 地点固定为西安
        HeWeatherWeatherHourlyResponse weather24h = weatherService.getWeather24h(heWeatherConfig.getLocation());
        log.info(JSONObject.toJSONString(weather24h));
        return weather24h;
    }
}

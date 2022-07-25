package site.hyxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.hyxy.annotation.AnonymousAccess;
import site.hyxy.rest.constants.IResult;
import site.hyxy.service.weather.he.HeWeatherService;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {
    @Autowired
    HeWeatherService heWeatherService;

    @RequestMapping("/now")
    @AnonymousAccess
    public IResult getWeatherNow() {
        heWeatherService.getWeatherNow();

        return IResult.success();
    }


    @RequestMapping("/24")
    @AnonymousAccess
    public IResult getWeather24H() {
        heWeatherService.getWeather24H();

        return IResult.success();
    }
}

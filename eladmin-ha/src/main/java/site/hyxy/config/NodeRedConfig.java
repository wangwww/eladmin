package site.hyxy.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

/**
 * @author hyxy
 */
@Configuration
@ConfigurationProperties(prefix = "nodered")
@Data
@Slf4j
public class NodeRedConfig {
    private String host;
    private String port;
    private String restContext;
    private Map<String, String> restUri;

    public String getNodeRedBaseUrl() {
        return "http://" + host + ":" + port + restContext;
    }

    public String getNodeRedUrl(String key) {
        if (!Objects.isNull(restUri) && restUri.containsKey(key) && StringUtils.isNotEmpty(restUri.get(key))) {
            return getNodeRedBaseUrl() + restUri.get(key);
        } else {
            log.error("nodered 接口配置信息不存在，接口配置：{}", Objects.isNull(restUri) ? null : JSONObject.toJSONString(restUri));
        }
        return null;
    }
}

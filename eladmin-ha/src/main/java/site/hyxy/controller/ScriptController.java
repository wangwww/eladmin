package site.hyxy.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.hyxy.entity.script.ScriptParam;
import site.hyxy.rest.constants.IResult;
import site.hyxy.utils.ShellUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author 王文文
 */
@Slf4j
@RestController
@RequestMapping("/v1/script")
public class ScriptController {
    @PostMapping("/run")
    public IResult run(@RequestBody ScriptParam scriptParam) {
        if (StringUtils.isEmpty(scriptParam.getCommand())) {
            return IResult.error().message("empty command");
        }

        ShellUtil.ShellResult result = null;
        try {
            String[] commandArr = scriptParam.getCommand().split(" ");
            log.info("执行命令： {}", Arrays.stream(commandArr).collect(Collectors.joining(" ")));
            result = ShellUtil.run(commandArr);
        } catch (IOException | InterruptedException e) {
            log.error("执行命令失败", e);
            return IResult.error().message("run command failed");
        }
        if (result.getCode() != 0) {
            log.error("执行命令失败：{}", JSONObject.toJSONString(result));
            return IResult.error().message("run command failed: "
                + result.getErrorMessage().stream().collect(Collectors.joining(",")));
        } else {
            return IResult.success().data(result.getSuccessMessage());
        }
    }
}

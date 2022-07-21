
package site.hyxy.entity.aiui;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Intent {
    @JSONField(name = "answer")
    private Answer answer;
    @JSONField(name = "no_nlu_result")
    private Long noNluResult;
    @JSONField(name = "operation")
    private String operation;
    @JSONField(name = "rc")
    private Long rc;

    /**
     * 技能的全局唯一名称，一般为vendor.name，vendor不存在时默认为IFLYTEK提供的开放技能。
     */
    @JSONField(name = "service")
    private String service;
    @JSONField(name = "category")
    private String category;
    @JSONField(name = "serviceCategory")
    private String serviceCategory;
    @JSONField(name = "serviceName")
    private String serviceName;
    @JSONField(name = "serviceType")
    private String serviceType;
    @JSONField(name = "sid")
    private String sid;
    @JSONField(name = "text")
    private String text;
    @JSONField(name = "uuid")
    private String uuid;
    @JSONField(name = "semantic")
    private List<Semantic> semantic;

    @JSONField(name = "voice_answer")
    private List<VoiceAnswer> voiceAnswer;

    @JSONField(name = "shouldEndSession")
    private boolean shouldEndSession = true;

    /**
     * 用于接收技能后处理脚本中构造的数据
     */
    @JSONField(name = "data")
    private IntentData data;

}

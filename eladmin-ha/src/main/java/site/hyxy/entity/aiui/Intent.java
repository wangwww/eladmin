
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
    private Answer mAnswer;
    @JSONField(name = "no_nlu_result")
    private Long mNoNluResult;
    @JSONField(name = "operation")
    private String mOperation;
    @JSONField(name = "rc")
    private Long mRc;

    /**
     * 技能的全局唯一名称，一般为vendor.name，vendor不存在时默认为IFLYTEK提供的开放技能。
     */
    @JSONField(name = "service")
    private String mService;
    @JSONField(name = "category")
    private String mCategory;
    @JSONField(name = "serviceCategory")
    private String mServiceCategory;
    @JSONField(name = "serviceName")
    private String mServiceName;
    @JSONField(name = "serviceType")
    private String mServiceType;
    @JSONField(name = "sid")
    private String mSid;
    @JSONField(name = "text")
    private String mText;
    @JSONField(name = "uuid")
    private String mUuid;
    @JSONField(name = "semantic")
    private List<Semantic> mSemantic;

    @JSONField(name = "voice_answer")
    private List<VoiceAnswer> mVoiceAnswer;

    @JSONField(name = "shouldEndSession")
    private boolean mShouldEndSession = true;

    /**
     * 用于接收技能后处理脚本中构造的数据
     */
    @JSONField(name = "data")
    private IntentData mData;

}

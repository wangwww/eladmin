
package site.hyxy.entity.aiui;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
@Getter
@Setter
@ToString
public class Data {
    @JSONField(name = "auth_id")
    private String mAuthId;
    @JSONField(name = "is_finish")
    private Boolean mIsFinish;
    @JSONField(name = "is_last")
    private Boolean mIsLast;
    @JSONField(name = "json_args")
    private JsonArgs mJsonArgs;
    @JSONField(name = "result_id")
    private Long mResultId;
    @JSONField(name = "sub")
    private String mSub;
    @JSONField(name = "text")
    private String mText;
    //返回值类型为TTS
    @JSONField(name = "content")
    private String mcontent;
    @JSONField(name = "intent")
    private Intent mintent;
}


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
    private String authId;
    @JSONField(name = "is_finish")
    private Boolean mIsFinish;
    @JSONField(name = "is_last")
    private Boolean mIsLast;
    @JSONField(name = "json_args")
    private JsonArgs jsonArgs;
    @JSONField(name = "result_id")
    private Long resultId;
    @JSONField(name = "sub")
    private String sub;
    @JSONField(name = "text")
    private String text;
    //返回值类型为TTS
    @JSONField(name = "content")
    private String content;
    @JSONField(name = "intent")
    private Intent intent;
}

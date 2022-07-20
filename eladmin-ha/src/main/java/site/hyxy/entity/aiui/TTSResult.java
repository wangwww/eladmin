
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
public class TTSResult {
    private String origin;

    public TTSResult() {
        this(null);
    }

    public TTSResult(String origin) {
        this.origin = origin;
    }

    @JSONField(name = "action")
    private String mAction;
    @JSONField(name = "code")
    private String mCode;
    @JSONField(name = "data")
    private Data mData;
    @JSONField(name = "desc")
    private String mDesc;
    @JSONField(name = "sid")
    private String mSid;


}

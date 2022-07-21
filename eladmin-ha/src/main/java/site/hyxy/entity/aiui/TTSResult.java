
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
    private String action;
    @JSONField(name = "code")
    private String code;
    @JSONField(name = "data")
    private Data data;
    @JSONField(name = "desc")
    private String desc;
    @JSONField(name = "sid")
    private String sid;


}


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
public class JsonArgs {
    @JSONField(name = "accent")
    private String accent;
    @JSONField(name = "language")
    private String language;
    @JSONField(name = "frame_id")
    private int frameId;
    private String cancel;
    private String dte;
    private String text_end;
    private String text_percent;
}

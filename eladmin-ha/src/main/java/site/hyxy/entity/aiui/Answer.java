
package site.hyxy.entity.aiui;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    @JSONField(name = "answerType")
    private String answerType;
    @JSONField(name = "emotion")
    private String emotion;
    @JSONField(name = "question")
    private Question question;
    @JSONField(name = "text")
    private String text;
    @JSONField(name = "topicID")
    private String topicID;
    @JSONField(name = "type")
    private String type;


}

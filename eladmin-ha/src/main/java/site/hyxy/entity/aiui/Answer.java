
package site.hyxy.entity.aiui;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    @JSONField(name = "answerType")
    private String mAnswerType;
    @JSONField(name = "emotion")
    private String mEmotion;
    @JSONField(name = "question")
    private Question mQuestion;
    @JSONField(name = "text")
    private String mText;
    @JSONField(name = "topicID")
    private String mTopicID;
    @JSONField(name = "type")
    private String mType;


}

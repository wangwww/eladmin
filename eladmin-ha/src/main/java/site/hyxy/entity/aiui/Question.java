
package site.hyxy.entity.aiui;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
@Getter
@Setter
public class Question {
    @JSONField(name = "question")
    private String mQuestion;
    @JSONField(name = "question_ws")
    private String mQuestionWs;
}

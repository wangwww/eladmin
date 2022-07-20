package site.hyxy.entity.aiui;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
public class Semantic {
    private String intent;
    private List<Map> slots;
}

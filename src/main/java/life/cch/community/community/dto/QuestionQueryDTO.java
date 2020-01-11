package life.cch.community.community.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2020-01-11 14:31:43
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}

package life.cch.community.community.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019-12-24 17:27:26
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}

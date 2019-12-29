package life.cch.community.community.dto;

import life.cch.community.community.model.User;
import lombok.Data;

/**
 * Created by codedrinker on 2019-12-26 16:06:52
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}

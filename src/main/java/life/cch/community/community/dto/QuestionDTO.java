package life.cch.community.community.dto;

import life.cch.community.community.model.User;
import lombok.Data;

/**
 * Created by codedrinker on 2019-12-16 14:15:55
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}

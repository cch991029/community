package life.cch.community.community.model;

import lombok.Data;

/**
 * Created by codedrinker on 2019-12-15 16:12:53
 */
@Data
public class Question {
    private int id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}

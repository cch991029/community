package life.cch.community.community.model;

import lombok.Data;

/**
 * Created by codedrinker on 2019-12-13 14:03:53
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}

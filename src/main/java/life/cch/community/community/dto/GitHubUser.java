package life.cch.community.community.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019-12-12 21:33:36
 */
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}

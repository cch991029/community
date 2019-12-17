package life.cch.community.community.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019-12-12 20:51:07
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}

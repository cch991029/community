package life.cch.community.community.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2020-01-05 18:29:46
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}

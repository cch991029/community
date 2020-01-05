package life.cch.community.community.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by codedrinker on 2020-01-02 16:22:01
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}

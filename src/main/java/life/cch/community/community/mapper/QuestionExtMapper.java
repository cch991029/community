package life.cch.community.community.mapper;

import life.cch.community.community.model.Question;
public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}
package life.cch.community.community.mapper;

import life.cch.community.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);
}
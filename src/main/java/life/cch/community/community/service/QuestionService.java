package life.cch.community.community.service;

import life.cch.community.community.dto.QuestionDTO;
import life.cch.community.community.mapper.QuestionMapper;
import life.cch.community.community.mapper.UserMapper;
import life.cch.community.community.model.Question;
import life.cch.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codedrinker on 2019-12-16 14:19:06
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> getQuestionDTOList() {
        List<Question> questions = questionMapper.getQuestionList();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}

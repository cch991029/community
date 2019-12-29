package life.cch.community.community.controller;

import life.cch.community.community.dto.CommentDTO;
import life.cch.community.community.dto.QuestionDTO;
import life.cch.community.community.enums.CommentTypeEnum;
import life.cch.community.community.service.CommentService;
import life.cch.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by codedrinker on 2019-12-19 16:22:14
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id") Long id, Model model) {
        //累加阅读数
        questionService.incView(id);

        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        return "question";
    }

    @GetMapping("/newYear")
    public String newYear() {
        return "NewYearCountdown";
    }
}

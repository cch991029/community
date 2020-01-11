package life.cch.community.community.controller;

import life.cch.community.community.dto.PaginationDTO;
import life.cch.community.community.dto.QuestionDTO;
import life.cch.community.community.mapper.QuestionMapper;
import life.cch.community.community.mapper.UserMapper;
import life.cch.community.community.model.User;
import life.cch.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by codedrinker on 2019-12-12 18:22:24
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "5") Integer size,
                        @RequestParam(value = "search",required = false) String search){
        PaginationDTO pagination = questionService.getQuestionDTOList(search,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        return "index";
    }
}
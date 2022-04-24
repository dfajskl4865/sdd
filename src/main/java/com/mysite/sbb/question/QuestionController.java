package com.mysite.sbb.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = questionService.getQuestion(id);

        model.addAttribute("question",question);

        return "question_detail";
    }

    @RequestMapping("/create")
    public String questionCreate(){
        return "question_from";
    }

    @PostMapping("/create")
    public String questionCreate(@RequestParam String subject, @RequestParam String content) {
        questionService.create(subject,content);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}
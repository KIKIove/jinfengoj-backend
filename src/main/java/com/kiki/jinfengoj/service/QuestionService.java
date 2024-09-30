package com.kiki.jinfengoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kiki.jinfengoj.model.dto.question.QuestionQueryRequest;
import com.kiki.jinfengoj.model.entity.Question;
import com.kiki.jinfengoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 82352
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-09-30 15:38:42
*/
public interface QuestionService extends IService<Question> {

    /**
     * 校验题目是否合法
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目封装
     * @param question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);
}

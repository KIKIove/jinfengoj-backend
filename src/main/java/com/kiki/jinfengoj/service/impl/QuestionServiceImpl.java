package com.kiki.jinfengoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kiki.jinfengoj.model.entity.Question;
import com.kiki.jinfengoj.service.UserService;
import com.kiki.jinfengoj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 82352
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-09-30 15:37:05
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements UserService.QuestionService {

}





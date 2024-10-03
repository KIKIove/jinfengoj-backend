package com.kiki.jinfengoj.judge;

import com.kiki.jinfengoj.judge.strategy.DefaultJudgeStrategy;
import com.kiki.jinfengoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.kiki.jinfengoj.judge.strategy.JudgeContext;
import com.kiki.jinfengoj.judge.strategy.JudgeStrategy;
import com.kiki.jinfengoj.judge.codesandbox.model.JudgeInfo;
import com.kiki.jinfengoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @author kiki
 * @Description 判题管理器,判断需要哪种判题策略
 */
@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
         JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
         if ("java".equals(language)){
             judgeStrategy = new JavaLanguageJudgeStrategy();
         }
         return judgeStrategy.doJudge(judgeContext);
    }
}

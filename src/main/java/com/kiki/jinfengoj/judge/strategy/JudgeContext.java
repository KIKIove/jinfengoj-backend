package com.kiki.jinfengoj.judge.strategy;

import com.kiki.jinfengoj.model.dto.question.JudgeCase;
import com.kiki.jinfengoj.judge.codesandbox.model.JudgeInfo;
import com.kiki.jinfengoj.model.entity.Question;
import com.kiki.jinfengoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @author kiki
 * @Description 提交策略上下文
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}

package com.kiki.jinfengoj.judge;

import com.kiki.jinfengoj.model.entity.QuestionSubmit;

/**
 * @author kiki
 * @Description 判题服务接口
 */
public interface JudgeService {
    QuestionSubmit doJudgeByDocker(long questionSubmitId);
    QuestionSubmit doJudgeByNative (long questionSubmitId);

}

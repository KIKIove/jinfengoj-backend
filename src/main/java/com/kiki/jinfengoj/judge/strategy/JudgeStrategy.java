package com.kiki.jinfengoj.judge.strategy;

import com.kiki.jinfengoj.judge.codesandbox.model.JudgeInfo;

/**
 * @author kiki
 * @Description 判题策略接口
 */
public interface JudgeStrategy {
    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}

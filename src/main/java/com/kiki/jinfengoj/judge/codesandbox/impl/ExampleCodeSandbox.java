package com.kiki.jinfengoj.judge.codesandbox.impl;

import com.kiki.jinfengoj.judge.codesandbox.CodeSandbox;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.kiki.jinfengoj.judge.codesandbox.model.JudgeInfo;
import com.kiki.jinfengoj.model.enums.JudgeInfoMessageEnum;
import com.kiki.jinfengoj.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author kiki
 * @Description 实例代码沙箱
 */
/**
 * 示例代码沙箱（仅为了跑通业务流程）
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }

    @Override
    public ExecuteCodeResponse executeCodeByDocker(ExecuteCodeRequest request) {
        return null;
    }

    @Override
    public ExecuteCodeResponse executeCodeByNative(ExecuteCodeRequest request) {
        return null;
    }
}


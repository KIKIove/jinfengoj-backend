package com.kiki.jinfengoj.judge.codesandbox;

import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author kiki
 * @Description 代码沙箱接口
 */
public interface CodeSandbox {
    ExecuteCodeResponse executeCodeByDocker(ExecuteCodeRequest request);

    ExecuteCodeResponse executeCodeByNative(ExecuteCodeRequest request);
}

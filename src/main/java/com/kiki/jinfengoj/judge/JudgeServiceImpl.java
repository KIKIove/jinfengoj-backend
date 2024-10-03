package com.kiki.jinfengoj.judge;

import cn.hutool.json.JSONUtil;
import com.kiki.jinfengoj.common.ErrorCode;
import com.kiki.jinfengoj.exception.BusinessException;
import com.kiki.jinfengoj.judge.codesandbox.CodeSandbox;
import com.kiki.jinfengoj.judge.codesandbox.CodeSandboxFactory;
import com.kiki.jinfengoj.judge.codesandbox.CodeSandboxProxy;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.kiki.jinfengoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.kiki.jinfengoj.judge.strategy.JudgeContext;
import com.kiki.jinfengoj.model.dto.question.JudgeCase;
import com.kiki.jinfengoj.judge.codesandbox.model.JudgeInfo;
import com.kiki.jinfengoj.model.entity.Question;
import com.kiki.jinfengoj.model.entity.QuestionSubmit;
import com.kiki.jinfengoj.model.enums.QuestionSubmitStatusEnum;
import com.kiki.jinfengoj.service.QuestionService;
import com.kiki.jinfengoj.service.QuestionSubmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kiki
 * @Description
 */
@Service
@Slf4j
public class JudgeServiceImpl implements JudgeService{

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudgeByDocker(long questionSubmitId) {
        // 1.根据id获取对应的题目、提交信息
        log.info("开始执行判题--------------------------");
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        // 如果不是等待状态
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题中");
        }
        // 更新提交信息状态
        log.info("开始更新提交信息状态-------------");
        QuestionSubmit updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setId(questionSubmitId);
        updateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(updateQuestionSubmit);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新提交信息失败");
        }
        // 2.调用代码沙箱，获取执行结果
        log.info("开始执行沙箱代码------------");
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        // 获取测试用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest request = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        // 实际调用代码沙箱
        ExecuteCodeResponse response = codeSandbox.executeCodeByDocker(request);
        List<String> outputList = response.getOutputList();
        // 3.根据执行结果进行判题，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeInfo(response.getJudgeInfo());
        judgeContext.setQuestionSubmit(questionSubmit);
        // 选择判题策略并返回判题结果
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 修改数据库中的信息
        log.info("开始更新数据库信息------------");
        updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setId(questionSubmitId);
        updateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        updateQuestionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(updateQuestionSubmit);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新提交信息失败");
        }
        // 再次查询数据库
        QuestionSubmit result = questionSubmitService.getById(questionSubmitId);
        return result;
    }

    @Override
    public QuestionSubmit doJudgeByNative(long questionSubmitId) {
        // 1.根据id获取对应的题目、提交信息
        log.info("开始执行判题--------------------------");
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        // 如果不是等待状态
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题中");
        }
        // 更新提交信息状态
        log.info("开始更新提交信息状态-------------");
        QuestionSubmit updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setId(questionSubmitId);
        updateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(updateQuestionSubmit);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新提交信息失败");
        }
        // 2.调用代码沙箱，获取执行结果
        log.info("开始执行沙箱代码------------");
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        // 获取测试用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest request = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        // 实际调用代码沙箱
        ExecuteCodeResponse response = codeSandbox.executeCodeByNative(request);
        List<String> outputList = response.getOutputList();
        // 3.根据执行结果进行判题，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeInfo(response.getJudgeInfo());
        judgeContext.setQuestionSubmit(questionSubmit);
        // 选择判题策略并返回判题结果
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 修改数据库中的信息
        log.info("开始更新数据库信息------------");
        updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setId(questionSubmitId);
        updateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        updateQuestionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(updateQuestionSubmit);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新提交信息失败");
        }
        // 再次查询数据库
        QuestionSubmit result = questionSubmitService.getById(questionSubmitId);
        return result;
    }
}
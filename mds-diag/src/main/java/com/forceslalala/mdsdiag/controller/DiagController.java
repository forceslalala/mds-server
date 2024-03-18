package com.forceslalala.mdsdiag.controller;

import com.forceslalala.mdsdiag.entity.DiagRecord;
import com.forceslalala.mdsdiag.entity.vo.Page;
import com.forceslalala.mdsdiag.entity.vo.Result;
import com.forceslalala.mdsdiag.service.DiagService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DiagController
 * @Author: wangtao
 * @Date: 2024/3/8 17:47
 * @Describe:
 */

@RestController
@RequestMapping("/api/v1/diag")
@Api(tags = "会诊列表接口")
public class DiagController {

    @Autowired
    private DiagService diagService;

    @PostMapping("/addRecord")
    @ApiOperation(value = "添加会诊记录")
    public Result<DiagRecord> register(@RequestBody @Valid DiagRecord diagRecord, BindingResult errors) {
        Result<DiagRecord> result;
        // 如果校验有错，返回注册失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        // 调用注册服务
        result = diagService.addInMongo(diagRecord);
        return result;
    }

    @PostMapping("/getRecord")
    @ApiOperation(value = "获取会诊记录")
    public Result<Page<DiagRecord>> getDiagRecord(@RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
                                            @RequestParam(name = "page_num", required = false, defaultValue = "1") int pageNum,
                                            @RequestParam(name = "doctor_id", required = true) int doctorId) {
        PageInfo<DiagRecord> pageInfo = diagService.getDiagRecord(pageNum, pageSize, doctorId);
        Page<DiagRecord> page = new Page<>(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPageNum(),
                pageInfo.getPageSize(), pageInfo.getPages());

        Result<Page<DiagRecord>> result = new Result<>();
        result.setData(page);
        result.setSuccess(true);
        return result;
    }
}

package com.forceslalala.mdsdiag.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

/**
 * @ClassName DiagRecord
 * @Author: wangtao
 * @Date: 2024/3/11 19:07
 * @Describe:
 */

@Data
@Document(collection = "diag_record")
public class DiagRecord implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;

    @MongoId
    private String id;

    @NotEmpty(message = "患者姓名不能为空")
    private String patientName;

    @NotEmpty(message = "医生ID不能为空")
    private Integer doctorId;

    @NotEmpty(message = "患者ID不能为空")
    private Integer patientId;

    @NotEmpty(message = "问诊部门不能为空")
    private String department;

    /**
     * 患者性别
     * 0-女
     * 1-男
     */
    @NotEmpty(message = "患者性别不能为空")
    private int gender;

    /**
     * 0-常规
     * 1-急诊
     */
    @NotEmpty(message = "问诊类型不能为空")
    private int type;

    /**
     * 0-待诊断
     * 1-诊断中
     * 2-检查中
     */
    @NotEmpty(message = "问诊状态不能为空")
    private int status;
}

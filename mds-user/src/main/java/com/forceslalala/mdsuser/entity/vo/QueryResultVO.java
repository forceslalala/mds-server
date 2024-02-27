package com.forceslalala.mdsuser.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName QueryResultVo
 * @Author: wangtao
 * @Date: 2024/2/26 15:38
 * @Describe:
 */

@Data
@NoArgsConstructor
public class QueryResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long totalRecords = -1;

    /**
     * 当前页记录列表
     */
    private List<T> records;
}

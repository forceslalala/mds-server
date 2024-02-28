package com.forceslalala.mdsuser.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Page
 * @Author: wangtao
 * @Date: 2024/2/26 17:20
 * @Describe:
 */

@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总记录数")
    private long total = -1;

    @ApiModelProperty(value = "当前页记录列表")
    private List<T> records;

    @ApiModelProperty(value = "当前页码")
    private int pageNum;

    @ApiModelProperty(value = "页面数据量")
    private int pageSize;

    @ApiModelProperty(value = "总页数")
    private int pages;

    public Page() {
    }

    public Page(long total, List<T> records, int pageNum, int pageSize, int pages) {
        this.total = total;
        this.records = records;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
    }
}

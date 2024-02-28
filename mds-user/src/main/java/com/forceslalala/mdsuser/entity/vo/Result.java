package com.forceslalala.mdsuser.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Author: wangtao
 * @Date: 2024/2/27 11:18
 * @Describe:
 */

@Data
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "是否操作成功")
    private boolean success;

    @ApiModelProperty(value = "返回的数据主体")
    private T data;

    /**
     * 设定结果为成功
     *
     * @param msg 消息
     */
    public void setResultSuccess(String msg) {
        this.message = msg;
        this.success = true;
        this.data = null;
    }

    /**
     * 设定结果为成功
     *
     * @param msg  消息
     * @param data 数据体
     */
    public void setResultSuccess(String msg, T data) {
        this.message = msg;
        this.success = true;
        this.data = data;
    }

    /**
     * 设定结果为失败
     *
     * @param msg 消息
     */
    public void setResultFailed(String msg) {
        this.message = msg;
        this.success = false;
        this.data = null;
    }
}

package com.forceslalala.mdsdiag.entity.vo;


import lombok.Data;
import org.springframework.data.domain.Sort.Direction;

/**
 * @ClassName PageVo
 * @Author: wangtao
 * @Date: 2024/2/26 15:51
 * @Describe:
 */

@Data
public class PageVo {

    private int pageNum;
    private int pageSize;
    private String sort;
    private String order = Direction.DESC.toString();

    public PageVo() {

    }

    public PageVo(int pageNum, int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageVo(int pageNum, int pageSize, String sort, String order) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sort = sort;
        this.order = order;
    }
}

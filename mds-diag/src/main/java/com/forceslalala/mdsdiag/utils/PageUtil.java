package com.forceslalala.mdsdiag.utils;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.forceslalala.mdsdiag.entity.vo.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName PageUtil
 * @Author: wangtao
 * @Date: 2024/2/26 15:51
 * @Describe:
 */
public class PageUtil {

    public static Pageable initPage(PageVo page) {
        Pageable pageable = null;
        int pageNumber = page.getPageNum();
        int pageSize = page.getPageSize();
        String order = page.getOrder();
        String sort = page.getSort();

        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (StringUtils.isNotEmpty(sort)) {
            List<String> sorts = new ArrayList<>(Arrays.asList(sort.split(",")));
            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < sorts.size(); i++) {
                String field = sorts.get(i);
                Order entity = new Order(Direction.ASC.name().equals(order) ? Direction.ASC : Direction.DESC, field);
                orders.add(entity);
            }
            pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(orders));
        } else {
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        }
        return pageable;
    }
}

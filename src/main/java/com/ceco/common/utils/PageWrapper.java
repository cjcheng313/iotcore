package com.ceco.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class PageWrapper<T> implements Serializable {

    private long pageNum = 1;
    private long pageSize = 10;
    private long totalSize = 0;
    private List<T> records = Collections.emptyList();

    public static <T> PageWrapper<T> build(long pageNum, long pageSize) {
        PageWrapper<T> wrapper = new PageWrapper<>();
        wrapper.setPageNum(pageNum);
        wrapper.setPageSize(pageSize);
        return wrapper;
    }
    
    public static <T> PageWrapper<T> build(IPage<?> page, List<T> records) {
        PageWrapper<T> wrapper = new PageWrapper<>();
        wrapper.setPageNum(page.getCurrent());
        wrapper.setPageSize(page.getSize());
        wrapper.setTotalSize(page.getTotal());
        wrapper.setRecords(records);
        return wrapper;
    }
    
    public static <T> PageWrapper<T> build(long pageNum, long pageSize, long totalSize, List<T> records) {
        PageWrapper<T> wrapper = new PageWrapper<>();
        wrapper.setPageNum(pageNum);
        wrapper.setPageSize(pageSize);
        wrapper.setTotalSize(totalSize);
        wrapper.setRecords(records);
        return wrapper;
    }
    
    public static <Req,Resp> PageWrapper<Resp> build(Page<Req> page, List<Resp> records) {
        PageWrapper<Resp> wrapper = new PageWrapper<>();
        wrapper.setPageNum(page.getPageNum());
        wrapper.setPageSize(page.getPageSize());
        wrapper.setTotalSize(page.getTotal());
        wrapper.setRecords(records);
        return wrapper;
    }
    
}

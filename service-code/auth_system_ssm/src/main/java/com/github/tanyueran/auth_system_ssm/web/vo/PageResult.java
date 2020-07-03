package com.github.tanyueran.auth_system_ssm.web.vo;

import java.io.Serializable;

// 分页返回的结果对象
public class PageResult implements Serializable {
    private Integer total;// 总数
    private Integer totalPage;// 总页数
    private Integer current;// 当前页
    private Integer size;// 页容量
    private Object data;// 内容

    public PageResult() {
    }

    public PageResult(Integer total, Integer totalPage, Integer current, Integer size, Object data) {
        this.total = total;
        this.totalPage = totalPage;
        this.current = current;
        this.size = size;
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", totalPage=" + totalPage +
                ", current=" + current +
                ", size=" + size +
                ", data=" + data +
                '}';
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

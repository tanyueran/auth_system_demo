package com.github.tanyueran.auth_system.pojo;

// 用于分页查询
public class Page {
    private Integer size;// 页容量
    private Integer current;// 当前页
    private String keyword;// 关键字

    public Page() {
    }

    public Page(Integer size, Integer current, String keyword) {
        this.size = size;
        this.current = current;
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Page{" +
                "size=" + size +
                ", current=" + current +
                ", keyword='" + keyword + '\'' +
                '}';
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

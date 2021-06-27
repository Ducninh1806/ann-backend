package com.ducninh.ann.service.dto.search;

import java.util.List;

public abstract class BaseSearch {

    protected Long totalRecords;
    protected Integer totalPages;
    protected Integer page;
    protected Integer pageSize;
    protected List<?> data;
    protected List<OrderDTO> orders;

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "BaseSearch{" +
                "totalRecords=" + totalRecords +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", pageSize=" + pageSize +
                ", data=" + data +
                ", orders=" + orders +
                '}';
    }
}

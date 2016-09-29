package ru.spb.iac.storager.server.domain.shared;

import java.io.Serializable;
import java.util.List;

public class PagedResult<T> implements Serializable {

    public static <T> PagedResult<T> of(List<T> items, int page, int total) {
        PagedResult<T> result = new PagedResult<>();
        result.items = items;
        result.page = page;
        result.total = total;
        return result;
    }

    private List<T> items;
    private Integer page;
    private Integer total;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

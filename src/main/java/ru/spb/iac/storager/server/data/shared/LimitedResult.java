package ru.spb.iac.storager.server.data.shared;

import java.io.Serializable;
import java.util.List;

public class LimitedResult<T> implements Serializable {

    public static <T> LimitedResult<T> of(List<T> items, int offset, int total) {
        LimitedResult<T> result = new LimitedResult<>();
        result.items = items;
        result.offset = offset;
        result.total = total;
        return result;
    }

    private List<T> items;
    private Integer offset;
    private Integer total;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

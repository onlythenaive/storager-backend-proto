package ru.spb.iac.storager.server.domain.shared;

import java.util.ArrayList;
import java.util.List;

public final class PagedResult<T> {

    private final List<T> items;
    private final Integer page;
    private final Integer total;

    public PagedResult(final List<T> items, final int page, final int total) {
        this.items = new ArrayList<>(items);
        this.page = page;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotal() {
        return total;
    }
}

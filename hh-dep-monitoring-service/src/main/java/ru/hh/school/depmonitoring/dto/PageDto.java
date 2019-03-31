package ru.hh.school.depmonitoring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

public class PageDto<T extends Serializable> implements Serializable {
    @NotNull
    private List<T> items;

    private int found;

    private int pages;

    @DefaultValue("20")
    private int perPage;

    private int page;

    private PageDto() {
        this(builder());
    }

    private PageDto(PageDtoBuilder<T> builder) {
        this.items = builder.items;
        this.found = builder.found;
        this.pages = builder.pages;
        this.perPage = builder.perPage;
        this.page = builder.page;
    }

    public static <T extends Serializable> PageDtoBuilder<T> builder() {
        return new PageDtoBuilder<>();
    }

    public List<T> getItems() {
        return items;
    }

    public int getFound() {
        return found;
    }

    public int getPages() {
        return pages;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getPage() {
        return page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PageDto<?> pageDto = (PageDto<?>) o;
        return found == pageDto.found &&
                pages == pageDto.pages &&
                perPage == pageDto.perPage &&
                page == pageDto.page &&
                Objects.equals(items, pageDto.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, found, pages, perPage, page);
    }

    public static final class PageDtoBuilder<T extends Serializable> {
        private List<T> items;
        private int found;
        private int pages;
        private int perPage;
        private int page;

        private PageDtoBuilder() {
        }

        public PageDtoBuilder<T> withItems(List<T> items) {
            this.items = new ArrayList<>(items);
            return this;
        }

        public PageDtoBuilder<T> withFound(int found) {
            this.found = found;
            return this;
        }

        public PageDtoBuilder<T> withPages(int pages) {
            this.pages = pages;
            return this;
        }

        public PageDtoBuilder<T> withPerPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public PageDtoBuilder<T> withPage(int page) {
            this.page = page;
            return this;
        }

        public PageDto<T> build() {
            if (this.perPage <= 0) {
                throw new IllegalArgumentException("perPage field must be positive");
            }
            if (this.pages < 0) {
                throw new IllegalArgumentException("page field must not be negative");
            }
            if (items == null) {
                throw new IllegalArgumentException("items parameter must not be null");
            }
            return new PageDto<>(this);
        }
    }
}

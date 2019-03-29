package ru.hh.school.depmonitoring.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class PageDto<T extends Serializable> implements Serializable {
    @NotNull
    private T[] items;

    private int found;

    private int pages;

    @NotNull
    private Integer perPage;

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

    public T[] getItems() {
        return items;
    }

    public Integer getFound() {
        return found;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Integer getPage() {
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
        return Arrays.equals(items, pageDto.items) &&
                Objects.equals(found, pageDto.found) &&
                Objects.equals(pages, pageDto.pages) &&
                Objects.equals(perPage, pageDto.perPage) &&
                Objects.equals(page, pageDto.page);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(found, pages, perPage, page);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    public static final class PageDtoBuilder<T extends Serializable> {
        private T[] items;
        private int found;
        private int pages;
        private int perPage;
        private int page;

        private PageDtoBuilder() {
        }

        public PageDtoBuilder<T> withItems(T[] items) {
            this.items = Objects.requireNonNull(items, "Page items must not be null");
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
            if (perPage <= 0) {
                throw new IllegalArgumentException("perPage parameter must be positive");
            }
            this.perPage = perPage;
            return this;
        }

        public PageDtoBuilder<T> withPage(int page) {
            this.page = page;
            return this;
        }

        public PageDto<T> build() {
            return new PageDto<>(this);
        }
    }
}

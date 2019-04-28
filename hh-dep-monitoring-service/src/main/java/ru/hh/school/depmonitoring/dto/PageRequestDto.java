package ru.hh.school.depmonitoring.dto;


import javax.ws.rs.DefaultValue;
import java.io.Serializable;

public class PageRequestDto implements Serializable {

    @DefaultValue("100")
    private int perPage;

    private int page;

    @DefaultValue("")
    private String searchString;

    private boolean ascending;

    private PageRequestDto() {
        this(builder());
    }

    private PageRequestDto(PageRequestDtoBuilder builder) {
        this.perPage = builder.perPage;
        this.page = builder.page;
        this.searchString = builder.searchString;
        this.ascending = builder.ascending;
    }

    public static PageRequestDtoBuilder builder() {
        return new PageRequestDtoBuilder();
    }

    public int getPerPage() {
        return perPage;
    }

    public int getPage() {
        return page;
    }

    public String getSearchString() {
        return searchString;
    }

    public boolean isAscending() {
        return ascending;
    }

    public static final class PageRequestDtoBuilder {
        private int perPage;
        private int page;
        private String searchString;
        private boolean ascending;

        private PageRequestDtoBuilder() {
        }

        public PageRequestDtoBuilder withPerPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public PageRequestDtoBuilder withPage(int page) {
            this.page = page;
            return this;
        }

        public PageRequestDtoBuilder withSearchString(String searchString) {
            this.searchString = searchString;
            return this;
        }

        public PageRequestDtoBuilder withAscending(boolean ascending) {
            this.ascending = ascending;
            return this;
        }

        public PageRequestDto build() {
            return new PageRequestDto(this);
        }
    }

}

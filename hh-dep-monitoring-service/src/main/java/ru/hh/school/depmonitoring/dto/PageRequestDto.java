package ru.hh.school.depmonitoring.dto;

import java.io.Serializable;
import javax.ws.rs.DefaultValue;

public class PageRequestDto implements Serializable {

    @DefaultValue("20")
    private int perPage;

    private int page;

    private PageRequestDto() {
        this(builder());
    }

    private PageRequestDto(PageRequestDtoBuilder builder) {
        this.perPage = builder.perPage;
        this.page = builder.page;
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


    public static final class PageRequestDtoBuilder {
        private int perPage;
        private int page;

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

        public PageRequestDto build() {
            return new PageRequestDto(this);
        }
    }

}

package ru.hh.school.depmonitoring.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class PageRequestDto implements Serializable {

    private final int perPage;

    private final int page;

    @NotNull
    private final String searchString;

    @NotNull
    private final List<PageSort> pageSortList;

    public PageRequestDto() {
        this(builder());
    }

    private PageRequestDto(PageRequestDtoBuilder builder) {
        this.perPage = builder.perPage;
        this.page = builder.page;
        this.searchString = builder.searchString;
        this.pageSortList = Objects.requireNonNullElseGet(builder.pageSortList, List::of);
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

    public List<PageSort> getPageSortList() {
        return pageSortList;
    }

    public static final class PageRequestDtoBuilder {
        private int perPage;
        private int page;
        private String searchString;
        private List<PageSort> pageSortList;

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

        public PageRequestDtoBuilder withOrderPointsList(List<PageSort> list) {
            this.pageSortList = list;
            return this;
        }

        public PageRequestDto build() {
            return new PageRequestDto(this);
        }
    }


    public static final class PageSort implements Serializable {
        private String property;
        private boolean ascending;

        private PageSort() {

        }

        private PageSort(String property, boolean ascending) {
            this.property = property;
            this.ascending = ascending;
        }

        public static PageSort createOrderPoint(String field, boolean ascending) {
            if (isBlank(field)) {
                throw new IllegalArgumentException("Order field is empty");
            }
            return new PageSort(field, ascending);
        }

        public String getProperty() {
            return property;
        }

        public void setField(String property) {
            this.property = property;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        public static PageSort valueOf(String string) {
            if (isBlank(string)) {
                throw new IllegalArgumentException("Error while parsing PageSort");
            }
            String[] array = string.split(",");
            boolean ascending = true;
            if (isBlank(array[0])) {
                throw new IllegalArgumentException("Error while parsing sorting field");
            }
            if (!isBlank(array[1])) {
                if (array[1].equalsIgnoreCase("desc")) {
                    ascending = false;
                } else if (!array[1].equalsIgnoreCase("asc")) {
                    throw new IllegalArgumentException("Error while parsing sorting order");
                }
            }
            return new PageSort(array[0], ascending);
        }

    }

}

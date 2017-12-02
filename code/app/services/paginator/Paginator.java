package services.paginator;

import entities.pagination.Pagination;

import java.util.List;

public final class Paginator {
    public static <T> List<T> paginate(Pagination<T> input) {
        int itemPerPage = input.getItemPerPage();
        int currentPage = input.getCurrentPage();
        List<T> data = input.getData();
        int dataSize = data.size();

        int pageCount = dataSize / itemPerPage;
        if (dataSize % itemPerPage != 0) { pageCount++; }
        if (currentPage > pageCount) { currentPage = 1; }
        input.setPageCount(pageCount);

        if (dataSize > itemPerPage) {
            int beginIndex = (currentPage - 1) * itemPerPage;
            int endIndex = currentPage * itemPerPage;
            input.setData(data.subList(beginIndex, (endIndex >= dataSize) ? dataSize : endIndex));
        }

        return input.getData();
    }
}

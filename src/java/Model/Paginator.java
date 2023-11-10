package Model;

import java.util.List;

public class Paginator<T> {
    private List<T> items;
    private int currentPage;
    private int totalPages;
    private int recordsPerPage;

    public Paginator(List<T> items, int currentPage, int totalPages, int recordsPerPage) {
        this.items = items;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.recordsPerPage = recordsPerPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }
}

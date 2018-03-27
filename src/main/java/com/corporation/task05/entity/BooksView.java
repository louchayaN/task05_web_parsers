package com.corporation.task05.entity;

import java.util.List;

public class BooksView {

    private int currentPage;
    private int totalPageCount;
    private List<Book> currentViewBooks;

    public BooksView() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<Book> getCurrentViewBooks() {
        return currentViewBooks;
    }

    public void setCurrentViewBooks(List<Book> currentViewBooks) {
        this.currentViewBooks = currentViewBooks;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + currentPage;
        result = prime * result + ((currentViewBooks == null) ? 0 : currentViewBooks.hashCode());
        result = prime * result + totalPageCount;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BooksView other = (BooksView) obj;
        if (currentPage != other.currentPage)
            return false;
        if (currentViewBooks == null) {
            if (other.currentViewBooks != null)
                return false;
        } else if (! currentViewBooks.equals(other.currentViewBooks))
            return false;
        if (totalPageCount != other.totalPageCount)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BooksView [currentPage=" + currentPage + ", totalPages=" + totalPageCount + ", currentViewBooks="
                + currentViewBooks + "]";
    }

}

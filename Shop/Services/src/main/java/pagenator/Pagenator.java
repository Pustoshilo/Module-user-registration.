package pagenator;


import java.io.Serializable;
import java.util.List;

public class Pagenator<T> implements Serializable {

    private static final long serialVersionUID = 4L;

    public List<T> objects;
    public long rowsOnPage;
    public long countPages;
    public long nextPage;

    public Pagenator() {
    }

    public Pagenator(List<T> objects, long rowsOnPage, long countPages, long nextPage) {
        this.objects = objects;
        this.rowsOnPage = rowsOnPage;
        this.countPages = countPages;
        this.nextPage = nextPage;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public long getRowsOnPage() {
        return rowsOnPage;
    }

    public void setRowsOnPage(long rowsOnPage) {
        this.rowsOnPage = rowsOnPage;
    }

    public long getCountPages() {
        return countPages;
    }

    public void setCountPages(long countPages) {
        this.countPages = countPages;
    }

    public long getNextPage() {
        return nextPage;
    }

    public void setNextPage(long nextPage) {
        this.nextPage = nextPage;
    }
}

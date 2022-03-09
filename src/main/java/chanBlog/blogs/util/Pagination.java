package chanBlog.blogs.util;

public class Pagination {
    private int totalRows; // 전체 행의 수
    private int perPage; // 페이지당 행 수
    private int perPagination; // 화면에 나타나는 페이지 번호 수
    private int firstRow; // 시작 행
    private int endRow; // 마지막 행

    private int totalPages; // 전체 페이지 수
    private int curPageNo; // 현재 페이지 번호
    private int beginPageNo; // 시작 페이지 번호
    private int endPageNo; // 마지막 페이지 번호

    public Pagination(int curPageNo , int perPage , int perPagination, int totalRows){
        firstRow =  (curPageNo - 1) * perPage + 1; // 첫 페이지는 행번호 1부터 시작, 두번째 페이지는 페이지당 행 수 + 1
        endRow = firstRow + perPage - 1;

        totalPages = totalRows / perPage;

        if((totalRows % perPage) > 0)
            totalPages++;

        beginPageNo = 0 ;
        endPageNo = 0;

        if(totalPages > 0) {
            // totalpages(총페이지) 5, perPagination 3(3개씩), 1~3 / 4 5,
            // curPageNo(현재) = 5 (5 -1)/3 = 1 * 3 + 1 = 4, endPage = 6 > totalpages -> 5
            beginPageNo = (curPageNo - 1) / perPagination * perPagination + 1;
            endPageNo = beginPageNo + perPagination - 1;
            if(endPageNo > totalPages)
                endPageNo = totalPages;
        }
        setPerPagination(perPage);
        setBeginPageNo(beginPageNo);
        setEndPageNo(endPageNo);
        setCurPageNo(curPageNo);
        setTotalPages(totalPages);
        setPerPagination(perPagination);
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPerPagination() {
        return perPagination;
    }

    public void setPerPagination(int perPagination) {
        this.perPagination = perPagination;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurPageNo() {
        return curPageNo;
    }

    public void setCurPageNo(int curPageNo) {
        this.curPageNo = curPageNo;
    }

    public int getBeginPageNo() {
        return beginPageNo;
    }

    public void setBeginPageNo(int beginPageNo) {
        this.beginPageNo = beginPageNo;
    }

    public int getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(int endPageNo) {
        this.endPageNo = endPageNo;
    }
}

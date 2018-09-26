package com.health.common;

import lombok.Data;

@Data
public class Pager {
    /**
     * 当前页页码
     **/
    private int currentPage = 1;

    /**
     * 总页数
     **/
    private int totalPageCount = 0;

    /**
     * 总记录数
     **/
    private int totalCount = 0;

    /**
     * 每页的记录数
     **/
    private int pageSize = 20;
    
    /**
     * 每页起始行
     **/
    private int startIndex = 0;

    /**
     * 默认构造器
     */
    public Pager() {

    }

    /**
     * 构造器
     *
     * @param currentPage
     *
     */
    public Pager(final Integer currentPage) {
        if (currentPage == null || currentPage <= 0) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }
        setStartIndex((this.currentPage - 1) * pageSize);
    }

    /**
     * 构造器
     *
     * @param recordPerPage
     *
     * @param currentPage
     *
     */
    public Pager(final int recordPerPage, final int currentPage) {
        if (recordPerPage <= 0) {
            throw new IllegalArgumentException("每页记录数不能小于0");
        }
        if (currentPage <= 0) {
            throw new IllegalArgumentException("当前页码不能小于0");
        }
        pageSize = recordPerPage;
        this.currentPage = currentPage;
        setStartIndex((this.currentPage - 1) * pageSize);
    }

    /**
     * 当前页码
     *
     * @return {@link #currentPage}
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 获取后一页
     *
     * @return 后一页页码
     */
    public int getNextPage() {
        if (hasNextPage()) {
            return currentPage + 1;
        }
        return totalPageCount;
    }

    /**
     * 默认0
     *
     * @return {@link #totalPageCount}
     */
    public int getPageCount() {
        return totalPageCount;
    }

    /**
     * 获取上一页
     *
     * @return 上一页页码
     */
    public int getPrePage() {
        if (hasPrePage()) {
            return currentPage - 1;
        }
        return 1;
    }

    /**
     * @return {@link #totalCount}
     */
    public int getRecordCount() {
        return totalCount;
    }

    /**
     * 是否有后一页
     *
     * @return 有后一页：true，反之false
     */
    public boolean hasNextPage() {
        if (currentPage < totalPageCount) {
            return true;
        }
        return false;
    }

    /**
     * 是否有前一页
     *
     * @return 有前一页：true，反之false
     */
    public boolean hasPrePage() {
        if (currentPage > 1) {
            return true;
        }
        return false;
    }

    /**
     * @param currentPage
     *            {@link #currentPage}
     */
    public void setCurrentPage(final int currentPage) {
        if (currentPage <= 0) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }
        setStartIndex((this.currentPage - 1) * pageSize);
    }

    /**
     * @param totalPageCount
     *            {@link #totalPageCount}
     */
    public void setTotalPageCount(final int totalPageCount) {
        if (totalPageCount < 0) {
            throw new IllegalArgumentException("总页数不能小于0");
        }
        this.totalPageCount = totalPageCount;
    }

    /**
     * @param totalCount
     *            {@link #totalCount}
     */
    public void setTotalCount(final int totalCount) {
        if (totalCount < 0) {
            throw new IllegalArgumentException("总记录数不能小于0");
        }
        this.totalCount = totalCount;
        totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        startIndex = (currentPage - 1) * pageSize;
    }

    /**
     * @param pageSize
     *            {@link #pageSize}
     */
    public void setPageSize(final int pageSize) {
        if (pageSize < 0) {
            throw new IllegalArgumentException("每页记录数不能小于0");
        }
        this.pageSize = pageSize;
    }

    /**
     * @param startIndex
     *            {@link #startIndex}
     */
    public void setStartIndex(final int startIndex) {
        if (startIndex < 0) {
            throw new IllegalArgumentException("每页起始行不能小于0");
        }
        this.startIndex = startIndex;
    }
    
    
    public int getTotalPageCount() {
        return totalPageCount;
    }
    
    public int getTotalCount() {
        return totalCount;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public int getStartIndex() {
        return startIndex;
    }
}

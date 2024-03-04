package com.fastcampus.ch5.domain;

public class PageHandler {
    private SearchCondition sc;
    public final int NAV_SIZE = 10;
    private int totalCnt;
    private int totalPage;
    private int beginPage;
    private int endPage;
    private boolean showNext = false;
    private boolean showPrev = false;

    public PageHandler(int totalCnt, Integer page, Integer pageSize){
        this(totalCnt, new SearchCondition(page, pageSize));
    }

    public PageHandler(int totalCnt, SearchCondition sc){
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    private void doPaging(int totalCnt, SearchCondition sc){
        this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize()==0? 0:1);
        this.sc.setPage(Math.min(sc.getPage(), totalPage));
        this.beginPage = (this.sc.getPage()-1 / NAV_SIZE * NAV_SIZE +1);
        this.endPage = Math.min(beginPage + NAV_SIZE -1, totalPage);
        this.showPrev = beginPage!=1;
        this.showNext = endPage!=totalPage;
    }


}

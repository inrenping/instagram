package com.inrenping.instagram.entity.response;

import java.util.List;

public class EdgeOwnerToTimelineMedia {
    public Integer count;
    public PageInfo page_info;
    public List<Edge_> edges;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public PageInfo getPage_info() {
        return page_info;
    }

    public void setPage_info(PageInfo page_info) {
        this.page_info = page_info;
    }

    public List<Edge_> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge_> edges) {
        this.edges = edges;
    }
}

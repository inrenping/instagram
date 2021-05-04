package com.inrenping.instagram.entity.response;

public class FirstResponse {
    public String logging_page_id;
    public GrapHql graphql;

    public String getLogging_page_id() {
        return logging_page_id;
    }

    public void setLogging_page_id(String logging_page_id) {
        this.logging_page_id = logging_page_id;
    }

    public GrapHql getGraphql() {
        return graphql;
    }

    public void setGraphql(GrapHql graphql) {
        this.graphql = graphql;
    }
}

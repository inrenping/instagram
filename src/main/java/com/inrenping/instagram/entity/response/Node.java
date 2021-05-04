package com.inrenping.instagram.entity.response;

import java.util.List;

public class Node {
    public Long id;
    public String __typename;
    public String shortcode;
    public String display_url;
    public Long taken_at_timestamp;
    public Location location;
    public EdgeMediaPreviewLike edge_media_preview_like;
    public EdgeSidecarToChildren edge_sidecar_to_children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get__typename() {
        return __typename;
    }

    public void set__typename(String __typename) {
        this.__typename = __typename;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public Long getTaken_at_timestamp() {
        return taken_at_timestamp;
    }

    public void setTaken_at_timestamp(Long taken_at_timestamp) {
        this.taken_at_timestamp = taken_at_timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EdgeMediaPreviewLike getEdge_media_preview_like() {
        return edge_media_preview_like;
    }

    public void setEdge_media_preview_like(EdgeMediaPreviewLike edge_media_preview_like) {
        this.edge_media_preview_like = edge_media_preview_like;
    }

    public EdgeSidecarToChildren getEdge_sidecar_to_children() {
        return edge_sidecar_to_children;
    }

    public void setEdge_sidecar_to_children(EdgeSidecarToChildren edge_sidecar_to_children) {
        this.edge_sidecar_to_children = edge_sidecar_to_children;
    }
}

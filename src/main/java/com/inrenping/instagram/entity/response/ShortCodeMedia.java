package com.inrenping.instagram.entity.response;

public class ShortCodeMedia {
    public String __typename;
    public Long id;
    public String shortcode;
    public String display_url;
    public String tracking_token;
    public EdgeMediaPreviewLike edge_media_preview_like;
    public Location location;
    public EdgeSidecarToChildren edge_sidecar_to_children;

    public String get__typename() {
        return __typename;
    }

    public void set__typename(String __typename) {
        this.__typename = __typename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTracking_token() {
        return tracking_token;
    }

    public void setTracking_token(String tracking_token) {
        this.tracking_token = tracking_token;
    }

    public EdgeMediaPreviewLike getEdge_media_preview_like() {
        return edge_media_preview_like;
    }

    public void setEdge_media_preview_like(EdgeMediaPreviewLike edge_media_preview_like) {
        this.edge_media_preview_like = edge_media_preview_like;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EdgeSidecarToChildren getEdge_sidecar_to_children() {
        return edge_sidecar_to_children;
    }

    public void setEdge_sidecar_to_children(EdgeSidecarToChildren edge_sidecar_to_children) {
        this.edge_sidecar_to_children = edge_sidecar_to_children;
    }
}

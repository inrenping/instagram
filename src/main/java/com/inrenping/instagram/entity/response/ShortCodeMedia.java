package com.inrenping.instagram.entity.response;

public class ShortCodeMedia {
    public String __typename;
    public Long id;
    public String shortcode;
    public String display_url;
    public EdgeMediaPreviewLike edge_media_preview_like;
    public Long taken_at_timestamp;
    public Location location;
    public Owner owner;
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

    public EdgeMediaPreviewLike getEdge_media_preview_like() {
        return edge_media_preview_like;
    }

    public void setEdge_media_preview_like(EdgeMediaPreviewLike edge_media_preview_like) {
        this.edge_media_preview_like = edge_media_preview_like;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public EdgeSidecarToChildren getEdge_sidecar_to_children() {
        return edge_sidecar_to_children;
    }

    public void setEdge_sidecar_to_children(EdgeSidecarToChildren edge_sidecar_to_children) {
        this.edge_sidecar_to_children = edge_sidecar_to_children;
    }
}

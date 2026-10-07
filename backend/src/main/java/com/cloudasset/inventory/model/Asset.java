package com.cloudasset.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {

    private String assetType;
    private String name;
    private String displayName;
    private String location;
    private String project;
    private String state;

    private Map<String, Object> additionalAttributes;
}

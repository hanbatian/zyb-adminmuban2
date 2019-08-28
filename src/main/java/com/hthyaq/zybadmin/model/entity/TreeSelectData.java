package com.hthyaq.zybadmin.model.entity;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

@Data
public class TreeSelectData {
    private String title;
    private Integer value;
    private Integer key;
    private List<TreeSelectData> children = Lists.newArrayList();
}

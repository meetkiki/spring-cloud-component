package com.meetkiki.other;

import lombok.Data;

import java.util.List;

@Data
public class RankNode {

    /**
     * 唯一标识一个节点
     */
    String id;

    /**
     * 目前等级
     */
    Integer level;

    /**
     * 直接上级节点 只有一层
     */
    RankNode directParentRankNode;

    /**
     * 直接下级的Id，只包含一层
     */
    List<String> directSubordinateIds;

    public RankNode(String id) {
        this.id = id;
    }
}

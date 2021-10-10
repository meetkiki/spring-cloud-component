package com.meetkiki.other;

public interface NodeDomainService {

    /**
     * 创建或者节点更新节点
     */
    void save(RankNode node);

    /**
     * 根据唯一标识查询节点
     */
    RankNode findById(String id);

    /**
     * 绑定关联关系
     *
     * @param rankNodeId
     * @param reportRankNodeId
     */
    void bindNodeRelation(String rankNodeId, String reportRankNodeId);

    /**
     * 解除关联关系
     *
     * @param rankNodeId
     * @param reportRankNodeId
     */
    void releaseNodeRelation(String rankNodeId, String reportRankNodeId);

}

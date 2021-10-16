package com.thoughtworks.springcloud.feign.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeDomainServiceTest {


    /**
     * 新增的主要的case
     *
     *  1. 新增的时候成功 验证创建新增节点是否存在
     *  2. 新增的节点包含子节点的时候, 验证创建新增节点，能查看到子节点的绑定关系已更新
     *  3. 新增的节点包含子节点，但是子节点不存在场景，抛出异常 因为需要先创建子节点
     *  4. 新增的节点存在的时候,返回新增失败
     *  5. 新增的节点存在，但是子节点不存在时，仍然抛出异常
     *
     *  目前发现的问题：Node里面包含Node 的子节点，是否需要递归检验，或者说这里就应该设计为一个Id
     *
     * 查询的case
     *  1. 新增之后，可以通过Id查询这个节点信息
     *  2. 新增之后，由上级和下级关系，能够正常查询到
     *
     *  查询的问题：新增N个节点后，需要出现构造节点树的场景时，构造树的逻辑是否属于核心逻辑，该放在哪里
     */
    @Test
    void save() {


    }

    @Test
    void findById() {


    }
}
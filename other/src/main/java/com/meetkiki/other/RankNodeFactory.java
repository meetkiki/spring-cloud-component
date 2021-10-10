package com.meetkiki.other;


import org.springframework.stereotype.Service;

@Service
public class RankNodeFactory {

    private final IdService idService;

    public RankNodeFactory(IdService idService) {
        this.idService = idService;
    }

    public RankNode createNode() {
        return new RankNode(idService.generateId());
    }

}

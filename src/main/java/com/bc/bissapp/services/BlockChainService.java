package com.bc.bissapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bc.bissapp.entities.Block;

@Service
public class BlockChainService {

    private List<Block> chain = new ArrayList<>();

    public BlockChainService() {
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, System.currentTimeMillis(), "Genesis Block", "0");
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addData(String data) {
        Block lastBlock = getLastBlock();
        int newIndex = lastBlock.getIndex() + 1;
        String previousHash = lastBlock.getHash();
        Block newBlock = new Block(newIndex, System.currentTimeMillis(), data, previousHash);
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return chain;
    }
}

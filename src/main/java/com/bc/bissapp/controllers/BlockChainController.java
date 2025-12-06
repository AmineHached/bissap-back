package com.bc.bissapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bc.bissapp.entities.Block;
import com.bc.bissapp.services.BlockChainService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/blockchain")
@CrossOrigin
@AllArgsConstructor
public class BlockChainController {

    private final BlockChainService blockChainService;

    @GetMapping
    public List<Block> getBlockChain() {
        return blockChainService.getChain();
    }

    @PostMapping("/add")
    public Block addBlock(@RequestBody String data) {
        blockChainService.addData(data);
        return blockChainService.getLastBlock();
    }
}

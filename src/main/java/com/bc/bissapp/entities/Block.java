package com.bc.bissapp.entities;

import org.apache.commons.codec.digest.DigestUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Block {

    @Setter
    private int index;
    @Setter
    private long timestamp;
    private String data;
    private String previousHash;
    @Setter
    private String hash;

    public Block(int index, long timestamp, String data, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String blockData = this.index + this.timestamp + this.data + this.previousHash;
        return DigestUtils.sha256Hex(blockData);
    }

    // Override setter to auto-recalculate hash when data changes
    public void setData(String data) {
        this.data = data;
        this.hash = calculateHash();
    }

    // Also override setPreviousHash to recalculate when previousHash changes
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }
}

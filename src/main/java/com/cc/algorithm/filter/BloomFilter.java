package com.cc.algorithm.filter;

/*
 * author:  RainPoetry
 * date:  2019/10/11
 * description:  布隆过滤器
 */

public class BloomFilter {

    // 每一个 key 的 hash 次数
    private int k;

    // 每一个 key 占用的二进制 bit 数
    private int bitsPerKey;

    private int bitLen;

    private byte[] result;


    public BloomFilter(int k, int bitsPerKey) {
        this.bitsPerKey = bitsPerKey;
        this.k = k;
    }

   /* public byte[] generate(byte[][] keys) {
        assert keys != null : "keys is not allow null!";
        bitLen = keys.length * bitsPerKey;
        bitLen = ((bitLen + 7) / 8) << 3;
        bitLen = bitLen < 64 ? 64 : bitLen;
        result = new byte[bitLen >> 3];
        for (int i = 0; i < keys.length; i++) {
            assert keys[i] != null;

        }
    }*/
}

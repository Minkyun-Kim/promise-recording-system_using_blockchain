package com.blockchain.biz;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Util {
	/**
	 * For wallet, generate key pair
	 * @return KeyPair
	 */
	public static KeyPair generateKeyPair() {

        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generator.generateKeyPair();	
	}
	
	/**
	 * To get a hash string, return the 
	 * @param object
	 * @return
	 */
	public static String getObjectHash(Object object) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = digest.digest(object.toString().getBytes(StandardCharsets.UTF_8));
		return byteToHex(hash);
	}
	
	/**
	 * transfer byte array to hex string
	 * 
	 * @param byteArray
	 * @return String
	 */
	private static String byteToHex(byte[] byteArray) {
	    StringBuilder stringBuilder = new StringBuilder();

	    for(final byte _byte: byteArray)
	        stringBuilder.append(String.format("%02x ", _byte&0xff));

	    return stringBuilder.toString();
	}

	public static String getMerkleRoot(List<PromiseVO> promiseCollection) {
		List<String> merkleTree = new ArrayList<String>();
		for(PromiseVO promiseVO : promiseCollection) {
			merkleTree.add(Util.getObjectHash(promiseVO));
		}
		merkleTree.add(Util.getObjectHash(merkleTree.get(0) + merkleTree.get(1)));
		merkleTree.add(Util.getObjectHash(merkleTree.get(2) + merkleTree.get(3)));
		merkleTree.add(Util.getObjectHash(merkleTree.get(4) + merkleTree.get(5)));
		return merkleTree.get(6);
	}
}

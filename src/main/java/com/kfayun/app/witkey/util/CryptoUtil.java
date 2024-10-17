/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 加密处理工具类
 * 
 * @author Billy Zhang (billy_zh@126.com)
 */
public class CryptoUtil {

	/**
	 * MD5加密
	 * 
	 * @param s
	 * @return
	 */
	public static String MD5(String s) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] bytes = md.digest(s.getBytes("utf-8"));
	        return toHex(bytes);
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    
	    return "";
	}

	/**
	 * 字节数据转16进制字符
	 * 
	 * @param bytes
	 * @return
	 */
	private static String toHex(byte[] bytes) {

	    final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}

	/**
	 * 将16进制字符串转换为byte[]
	 *
	 * @param str
	 * @return
	 */
	public static byte[] toBytes(String str) {
		if(str == null || str.trim().equals("")) {
			return new byte[0];
		}

		byte[] bytes = new byte[str.length() / 2];
		for(int i = 0; i < str.length() / 2; i++) {
			String subStr = str.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(subStr, 16);
		}

		return bytes;
	}

	/**
     * DES加密 
	 * 
     * @param data 要加密的数据 
     * @return 
     */  
    public static String encryptDES(String key, String data) {  
        String s = "";  
        if ( data != null ) {  
        	try {
	            //DES算法要求有一个可信任的随机数源  
	            SecureRandom sr = new SecureRandom();  
	            //从原始密钥数据创建DESKeySpec对象  
	            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());  
	            //创建一个密钥工厂，用它将DESKeySpec转化成SecretKey对象  
	            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
	            SecretKey securekey = keyFactory.generateSecret(desKeySpec);  
	            //Cipher对象实际完成加密操作  
	            Cipher cipher = Cipher.getInstance("DES");  
	            //用密钥初始化Cipher对象  
	            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);  
	            //将加密后的数据编码成字符串  
	            s = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes())); 
        	} catch (Exception ex) {
        		ex.printStackTrace();
        	}
        }  
        return s;  
    }  
      
    /** 
     * DES解密 
	 * 
     * @param data 需要解密的数据 
     * @return 
     */  
    public static String decryptDES(String key, String data)  {  
        String s = "";  
        if ( data != null ) {  
        	try {
	            //DES算法要求有一个可信任的随机数源  
	            SecureRandom sr = new SecureRandom();  
	            //从原始密钥数据创建DESKeySpec对象  
	            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());  
	            //创建一个密钥工厂，用它将DESKeySpec转化成SecretKey对象  
	            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
	            SecretKey securekey = keyFactory.generateSecret(desKeySpec);  
	            //Cipher对象实际完成解密操作  
	            Cipher cipher = Cipher.getInstance("DES");  
	            //用密钥初始化Cipher对象  
	            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);  
	            //将加密后的数据解码再解密   
	            byte[] buf = cipher.doFinal(Base64.getDecoder().decode(data));  
	            s = new String(buf);  
        	} catch (Exception ex) {
        		ex.printStackTrace();
        	}
        }  
        return s;  
    }


	/**
	 * DES加密
	 * 
	 * @param data 要加密的数据
	 * @return
	 */
	public static String encryptDES2(String key, String data) {
		String s = null;
		if ( data != null ) {
			try {
				//DES算法要求有一个可信任的随机数源
				SecureRandom sr = new SecureRandom();
				//从原始密钥数据创建DESKeySpec对象
				DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
				//创建一个密钥工厂，用它将DESKeySpec转化成SecretKey对象
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				SecretKey securekey = keyFactory.generateSecret(desKeySpec);
				//Cipher对象实际完成加密操作
				Cipher cipher = Cipher.getInstance("DES");
				//用密钥初始化Cipher对象
				cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
				//将加密后的数据编码成字符串
				s = toHex(cipher.doFinal(data.getBytes()));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return s;
	}

	/**
	 * DES解密
	 * 
	 * @param data 要解密的数据
	 * @return
	 */
	public static String decryptDES2(String key, String data)  {
		String s = null;
		if ( data != null ) {
			try {
				//DES算法要求有一个可信任的随机数源
				SecureRandom sr = new SecureRandom();
				//从原始密钥数据创建DESKeySpec对象
				DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
				//创建一个密钥工厂，用它将DESKeySpec转化成SecretKey对象
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				SecretKey securekey = keyFactory.generateSecret(desKeySpec);
				//Cipher对象实际完成解密操作
				Cipher cipher = Cipher.getInstance("DES");
				//用密钥初始化Cipher对象
				cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
				//将加密后的数据解码再解密
				byte[] buf = cipher.doFinal(toBytes(data));
				s = new String(buf);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		return s;
	}
}

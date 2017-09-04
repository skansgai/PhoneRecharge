package huawei.com.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 
 * <p>Title: DESUtils.java</p>
 * <p>Description: DES->DES算法为密码体制中的对称密码体制，又被称为美国数据加密标准，
 * 是1972年美国IBM公司研制的对称密码体制加密算法。 明文按64位进行分组，密钥长64位，密钥事
 * 实上是56位参与DES运算（第8、16、24、32、40、48、56、64位是校验位， 使得每个密钥都有
 * 奇数个1）分组后的明文组和56位的密钥按位替代或交换的方法形成密文组的加密方法。</p>
 * <p>Company: isoftstone
 * @author yangsongsong
 * @date 2017年8月8日 
 * @version 1.0
 */
public class DESUtils {
    //算法名称
	public static final String KEY_ALGORITHM = "DES";    
    // 算法名称/加密模式/填充方式    
    public static final String CIPHER_ALGORITHM_ECB = "DES/ECB/PKCS5Padding";    
    public static final String CIPHER_ALGORITHM_CBC = "DES/CBC/PKCS5Padding";
    
    //主方法
    public static void main(String[] args) throws Exception {
		/*
		 * 使用ECB mode
		 * 密钥生成器 生成密钥
		 * ECBmode cannot use IV
		 */
        //生成密钥
    	byte[] key = generateKey();
    	System.out.println("打印密钥："+new String(key));
    	//进行加密
    	byte[] encrypt = encrypt("你好!".getBytes(),key);
    	//打印密文
    	System.out.println("密文："+new String(encrypt));
    	//进行解密
    	byte[] des = decrypt(encrypt, key);
    	//打印明文
    	System.out.println("打印明文："+new String(des));
   
    	System.out.println("还原密钥："+toKey(key));
    	/*
    	 * 使用CBC mode
    	 * 使用密钥工厂生成密钥，加密，解密。
    	 * iv：EDS in CBC mode and RSA ciphers with OAEP encoding opration.
    	 */
    	DESKeySpec dks = new DESKeySpec(key);
    	SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
    	SecretKey secretKey = factory.generateSecret(dks);
    	Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
    	cipher.init(Cipher.ENCRYPT_MODE, secretKey,new IvParameterSpec(getIV()));
    	byte[] enc = cipher.doFinal("你好#&*".getBytes());
    	
    	cipher.init(Cipher.DECRYPT_MODE,secretKey, new IvParameterSpec(getIV()));
    	byte[] dec = cipher.doFinal(enc);
    	
    	System.out.println(new String(dec));
    	
	}

    /**
     * 
     * <p>Title: getIV<／p>
     * <p>Description: <／p>
     * @return
     */
	private static byte[] getIV() {
		String iv = "asdfivh7";//IV length: must be 8 bytes long 
		return iv.getBytes();
	}
	
	/**
	 * 
	 * <p>Title: decrypt<／p>
	 * <p>Description: 解密<／p>
	 * @param encrypt 密文
	 * @param key key
	 * @return 明文，原文
	 * @throws Exception 异常
	 */
	private static byte[] decrypt(byte[] encrypt, byte[] key) throws Exception{
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(encrypt);
	}

	/**
	 * 
	 * <p>Title: encrypt<／p>
	 * <p>Description: <／p>
	 * @param bytes 原文
	 * @param key 密钥
	 * @return 秘闻
	 * @throws Exception 异常
	 */
	private static byte[] encrypt(byte[] bytes, byte[] key) throws Exception{
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(bytes);
	}

	/**
	 * 生成密钥
	 * <p>Title: generateKey<／p>
	 * <p>Description: <／p>
	 * @return 返回的密钥
	 * @throws Exception 
	 */
	private static byte[] generateKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
		keyGenerator.init(56);//des必须是56，此时初始化方法不必调用
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();
	}
	
	/**
	 * 还原密钥
	 * <p>Title: toKey<／p>
	 * <p>Description: <／p>
	 * @param key key
	 * @return 密钥
	 * @throws Exception 异常
	 */
	private static Key toKey(byte[] key) throws Exception{
		DESKeySpec des = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(des);
		return secretKey;
	}
}

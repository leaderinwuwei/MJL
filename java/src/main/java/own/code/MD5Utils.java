package own.code;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author CaptainWang
 * @since 2024/6/14
 */

public class MD5Utils {
    public MD5Utils() {
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var7) {
            throw new RuntimeException("UTF-8 is unsupported", var7);
        } catch (NoSuchAlgorithmException var8) {
            throw new RuntimeException("Not support MD5Util", var8);
        }

        StringBuilder hex = new StringBuilder(12);
        for (int i = 0; i < 6; i++) { // 只取前6个字节
            byte b = hash[i];
            hex.append(Integer.toHexString((b & 0xFF) >>> 4)); // 取高4位
            hex.append(Integer.toHexString(b & 0x0F)); // 取低4位
        }

        return hex.toString();
    }

    public static String sign(String appSecret, TreeMap<String, String> params) {
        StringBuilder paramValues = new StringBuilder();
        params.put("appSecret", appSecret);
        Iterator var3 = params.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var3.next();
            paramValues.append((String)entry.getValue());
        }

        System.out.println(md5(paramValues.toString()));
        return md5(paramValues.toString());
    }
}

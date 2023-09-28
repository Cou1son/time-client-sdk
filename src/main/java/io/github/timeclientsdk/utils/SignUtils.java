package io.github.timeclientsdk.utils;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author mitchell
 */
public class SignUtils {

    public static String getSign(String secretKey){
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        return sha256.digestHex(secretKey);

    }
}

package com.github.tanyueran.auth_system.beans;

import com.github.tanyueran.auth_system.utils.RsaUtil;
import org.springframework.context.annotation.Bean;

import java.security.PrivateKey;
import java.security.PublicKey;

// 公钥私钥
public class MyKeys {
    private static String PUBLIC_KEY_PATH = "F:\\GitHub-Project\\auth_system_demo\\service-code\\auth_system\\doc\\key\\public.key";
    private static String PRIVATE_KEY_PATH = "F:\\GitHub-Project\\auth_system_demo\\service-code\\auth_system\\doc\\key\\private.key";

    @Bean("publicKey")
    public PublicKey getPublicKey() throws Exception {
        return RsaUtil.getPublicKey(PUBLIC_KEY_PATH);
    }

    @Bean("privateKey")
    public PrivateKey getPrivateKey() throws Exception {
        return RsaUtil.getPrivateKey(PRIVATE_KEY_PATH);
    }
}

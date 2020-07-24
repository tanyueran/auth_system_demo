package com.github.tanyueran.auth_system_springboot.bean;

import com.github.tanyueran.auth_system_springboot.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

// 注入公钥私钥
@Component
public class KeyBean {

    @Bean("publicKey")
    public PublicKey getPublicKey(@Value("${key.public.url}") String publicUrl) throws Exception {
        return RsaUtil.getPublicKey(publicUrl);
    }

    @Bean("privateKey")
    public PrivateKey getPrivateKey(@Value("${key.private.url}") String privateUrl) throws Exception {
        return RsaUtil.getPrivateKey(privateUrl);
    }
}

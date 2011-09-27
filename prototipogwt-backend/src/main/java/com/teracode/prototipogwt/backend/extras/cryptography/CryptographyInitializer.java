package com.teracode.prototipogwt.backend.extras.cryptography;

import org.apache.log4j.Logger;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.jasypt.hibernate.encryptor.HibernatePBEEncryptorRegistry;
import org.springframework.stereotype.Service;

/**
 * @author maxi
 */
@Service("cryptographyInitializer")
public class CryptographyInitializer {
	
	private static Logger logger = Logger.getLogger(CryptographyInitializer.class);
	
    public CryptographyInitializer() {
    	
        logger.info("Initializing Jasypt String Encryptor");       
        
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setPassword("p1l@rc1t@.");

        StandardPBEStringEncryptor strEncryptor = new StandardPBEStringEncryptor();
        strEncryptor.setConfig(config);
       
        HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
        registry.registerPBEStringEncryptor("hibernateStringEncryptor", strEncryptor);
    }
    
}
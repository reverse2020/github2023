# github2023
https://www.thomasvitale.com/https-spring-boot-ssl-certificate/



# Generate an SSL certificate in a keystore
Let's open our Terminal prompt and write the following command to create a JKS keystore:

###keytool -genkeypair -alias springboot -keyalg RSA -keysize 4096 -storetype JKS -keystore springboot.jks -validity 3650 -storepass password

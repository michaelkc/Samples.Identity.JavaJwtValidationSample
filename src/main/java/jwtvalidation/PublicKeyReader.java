package jwtvalidation;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.spec.*;
import java.util.*;

public class PublicKeyReader {

  public static PublicKey fromBase64EncodedPublicKey(String base64EncodedPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, CertificateException {
	  
	  byte[] publicCertBytes = Base64.getDecoder().decode(base64EncodedPublicKey);
	  ByteArrayInputStream publicCertStream = new ByteArrayInputStream(publicCertBytes);
	  try
	  {
		  CertificateFactory cf = CertificateFactory.getInstance("X.509");
		  Certificate cert = cf.generateCertificate(publicCertStream);
		  PublicKey publicKey = cert.getPublicKey();
		  return publicKey;
	  }
	  finally
	  {
		  publicCertStream.close();
	  }
	  	
  }
}

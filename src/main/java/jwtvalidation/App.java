package jwtvalidation;
import java.security.interfaces.*;
import java.util.*;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.*;

public class App {

	public static void main(String[] args) throws Exception {
		
		String tokenIssuer =		"https://si-authzserver.vfltest.dk/";
		String token = 				"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ijg3SE1Ta1Y4ZVA2ajItVWZPeFFiZzlGS29ldyJ9.eyJjbGllbnRfaWQiOiJTSU9BdXRoVGVzdENsaWVudCIsInNjb3BlIjoiRGVmYXVsdCIsInN1YiI6ImN2cnVzZXIxQFBST0QuRExJIiwiaXNzIjoiaHR0cHM6Ly9zaS1hdXRoenNlcnZlci52Zmx0ZXN0LmRrLyIsImF1ZCI6Imh0dHBzOi8vc2ktYXV0aHpzZXJ2ZXJ0ZXN0Y2xpZW50LnZmbHRlc3QuZGsvIiwiZXhwIjoxNDg0NjY2MjEwLCJuYmYiOjE0ODQ2NjI2MTB9.i7389c4aHwqobzmOrn0N0PhZ8MfFErzXLNxElfOeOQbP7AJy8OCm9amFlB3vL9aJWKgFdRZhEHT9tcrvLh8j_iSJp3ElNm4HbAnG8HGbE6Pk2VQKcQG67SwDIivfHqWJPq7cdhcfPO6z23XTIBrH2K5o3POzaXkGqdk759qWNqLo9lcILI17r_xPSehRBtOgt0ig-0WnAOs7RYAKevJENXeKDD7QoXoWwvsbB9lZ7VB10CWBrLntPBio0qYlKNCg8O6o0cfaONmfKaPCuGrGULrDDV0l5EZm4mdhuxwT53umZog2bkiw1JEOnKkrR217G02TGjYZQ7760CP4cXEPjw";
		String issuerPublicKey = 	"MIIDHjCCAgagAwIBAgIQ5ASV/f2iwopKWeXJvRMdFzANBgkqhkiG9w0BAQsFADAY" + 
									"MRYwFAYDVQQDEw1zaS1qd3RzaWduaW5nMCAXDTE0MTIzMTIyMDAwMFoYDzI5OTgx" + 
									"MjMxMjIwMDAwWjAYMRYwFAYDVQQDEw1zaS1qd3RzaWduaW5nMIIBIjANBgkqhkiG" + 
									"9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqt5cWeyYUrV4V0TuhopvpxaJSo3+rGxou7cX" + 
									"2midBFOSndTsFSOSLypSt9fzqaCtQfLvpEQAU5g8UdfkTElfDEWMyWL+oBCmaMix" + 
									"6G1sPqOzx4ekBOUbXRYDdcqV8v8a8I/SomWUmhBi+c584s/K778rvR/vVn9Idq85" + 
									"RBTfOGuqai+YJHe8VnuPGk4zP6H4GfgkGY/8n58+G/It0RGhTCv05tX+PeYtzXry" + 
									"UthG+aSVqZSuu8ro/iip2FZ/yQUskmcDDRfvYatnkPrZ2FwDMsjOuKAHe/cjvGJm" + 
									"YNz7i4nXrR1hGZLUI3fb+w0GXAd6w+ffUq0gq0aS/UeC5/G26QIDAQABo2IwYDAT" + 
									"BgNVHSUEDDAKBggrBgEFBQcDAzBJBgNVHQEEQjBAgBDNyiF38NRB8aMWRri2DzUr" + 
									"oRowGDEWMBQGA1UEAxMNc2ktand0c2lnbmluZ4IQ5ASV/f2iwopKWeXJvRMdFzAN" + 
									"BgkqhkiG9w0BAQsFAAOCAQEAORW+T73Q+KlnPulXp8tWAX8d2ZIPDlT06Bgyhxyg" + 
									"zXwuc66DGd4q4tAKQn5X9J0TX5exRnhCqlabKov0Wnh93mNBcBeCtEotGR6whQRe" + 
									"OnRTAJbW2QiWcD9sW0Tx0hnNpO4KHwavyVzbhJ/ME7r7fSjJle8GpOIbrRhRdCmC" + 
									"RIMdMQxVX0wX0LPexVyXVK/U1hLkxYbBz8/LUrbWlg0ukcOcNbIBQdkqzT+Ev49J" + 
									"bOoD5NXT9MYsF0XQS6M3B2tUuiS0covdWpseA8xctYgdeyW6NmkxX5jz1hSR433C" + 
									"u8fACGK/KBh6SGpLRZrMliGKhPW9CLBod4WFN3EhLONU1Q=="; 
									 
		
		
		try {
			System.out.println("Reading public certificate of JWT signer");
		    RSAKey key =  (RSAKey)PublicKeyReader.fromBase64EncodedPublicKey(issuerPublicKey);
		    System.out.println("Preparing token signature / issuer (" + tokenIssuer + ") validation");
		    JWTVerifier verifier = JWT.require(Algorithm.RSA256(key))
		            .withIssuer(tokenIssuer)
		            .build(); //Reusable verifier instance
		    System.out.println("Validating JWT token");
		    DecodedJWT jwt = verifier.verify(token);
		    System.out.println("Validated token:");
		    System.out.println("Claim types : Claim values");
		    System.out.println("--------------------------------------");
		    for (Map.Entry<String, Claim> entry : jwt.getClaims().entrySet())
		    {
		    	System.out.println(entry.getKey() + " : " + entry.getValue().asString());
		    }
		} catch (JWTDecodeException exception){
			System.out.println("ERROR: Invalid token.");
		}
		System.out.println("Done.");
	}

}

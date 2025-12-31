package com.Inwarrenty.Utils;

import java.util.Map;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDBConfig {
	private static VaultConfig vaultconfig;
	private static Vault vault1;
	
	
	static {

		try {
			vaultconfig  = new VaultConfig()
					.address("http://43.205.115.34:8200/")
					.token("root")
					.build();
		} catch (VaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 vault1 = new Vault(vaultconfig);
	}
	
	private VaultDBConfig() {
		
	}
	public static String getSecret(String key) {
		LogicalResponse responce = null;
		try {
			responce = vault1.logical().read("secret/phoenix/qa/database");
		} catch (VaultException e) {
			
			e.printStackTrace();
			return null;
		}
		Map<String, String> datamap = responce.getData();
		 String Secretkey=datamap.get(key);
		 return Secretkey;
	}

}

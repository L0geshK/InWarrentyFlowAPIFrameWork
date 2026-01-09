package com.Inwarrenty.Utils;

import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

import io.qameta.allure.Step;

public class VaultDBConfig {
	private static VaultConfig vaultconfig;
	private static Vault vault1;
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(VaultDBConfig.class);

	
	
	static {

		try {
			vaultconfig  = new VaultConfig()
					.address("http://43.205.115.34:8200/")
					.token("root")
					.build();
		} catch (VaultException e) {
			log.error("Something went wrong",e);
			e.printStackTrace();
		}
		
		 vault1 = new Vault(vaultconfig);
	}
	
	private VaultDBConfig() {
		
	}
	@Step("Retriving the Secret From Vault")
	public static String getSecret(String key) {
		LogicalResponse responce = null;
		try {
			responce = vault1.logical().read("secret/phoenix/qa/database");
		} catch (VaultException e) {
			log.error("Something went wrong",e);
			e.printStackTrace();
			return null;
		}
		Map<String, String> datamap = responce.getData();
		 String Secretkey=datamap.get(key);
		 return Secretkey;
	}

}

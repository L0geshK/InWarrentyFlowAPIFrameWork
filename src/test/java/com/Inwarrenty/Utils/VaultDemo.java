package com.Inwarrenty.Utils;

import java.util.Map;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDemo {
	public static void main(String[] args) throws VaultException {
		
		VaultConfig vault = new VaultConfig()
				.address("http://43.205.115.34:8200/")
				.token("root")
				.build();
		
		Vault vault1 = new Vault(vault);
		
		LogicalResponse responce=vault1.logical().read("secret/phoenix/qa/database");
		Map<String, String> map = responce.getData();
		System.out.println(map.get("DB_URL"));
	}

}

package br.com.vinicius.bankapi;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

	public static String removeMask(String string) {
		return string.replaceAll("[^\\d]", "");
	}
}

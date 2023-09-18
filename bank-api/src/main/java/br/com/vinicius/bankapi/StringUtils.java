package br.com.vinicius.bankapi;

public class StringUtils {

	public static String removeMask(String string) {
		return string.replaceAll("[^\\d]", "");
	}
}

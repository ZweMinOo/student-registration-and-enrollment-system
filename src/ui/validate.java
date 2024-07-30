package ui;
/**
 * <h1> validate </h1>
 * 
 * @author Zwe_Min_Oo
 * @version 1.0.0
 * @since 2018-06-30
 */
public class validate {
	
	 	public static boolean isNotNull(Object obj) {
	        if (obj == null) return true;
	        return false;
	    }
	    public static boolean isNotEmpty(String s) {
	        if (s.isEmpty()) return true;
	        return false;
	    }
	    public static boolean isName(String s) {
	        if (s.matches("([A-Z][a-zA-Z]*\\s*)*")) return true;
	        return false;
	    }
	    public static void isPassword(String s) {
	        if (s.matches("[a-zA-Z0-9 ]*")) throw new IllegalArgumentException("Please check your password");
	    }
//	    public static void isEmail(String s) {
//	        if (!s.matches("[A-Za-z0-9_]*[@][a-zA-Z]+[.][c][o][m]")) throw new IllegalArgumentException("Email should be format 'example@mail.com");
//	    }
//	    public static void isCity(String s) {
//	        if (s.matches("!([A-Z][a-zA-Z]*\\s*)*")) throw new IllegalArgumentException("City name must be start with capital letter");
//	    }
	    public static boolean isPhone(String s) {
	        if (s.matches("(02|09)?-\\d{9}")) return true;
	        return false;
	    }
}
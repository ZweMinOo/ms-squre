package ui;

public class validate {
	 public static void isNotNull(Object obj) {
	        if (obj == null) throw new IllegalArgumentException("illegal null");
	    }
	    public static void isNotEmpty(String s) {
	        if (s.isEmpty()) throw new IllegalArgumentException("illegal empty string");
	    }
	    public static void isName(String s) {
	        if (!s.matches("([A-Z][a-zA-Z]*\\s*)*")) throw new IllegalArgumentException("Name must be start with capital letter");
	    }
	    public static void isPassword(String s) {
	        if (s.matches("[a-zA-Z0-9 ]*")) throw new IllegalArgumentException("Please check your password");
	    }
	    public static void isEmail(String s) {
	        if (!s.matches("[A-Za-z0-9_]*[@][a-zA-Z]+[.][c][o][m]")) throw new IllegalArgumentException("Email should be format 'example@mail.com");
	    }
	    public static void isCity(String s) {
	        if (s.matches("!([A-Z][a-zA-Z]*\\s*)*")) throw new IllegalArgumentException("City name must be start with capital letter");
	    }
	    public static void isPhone(String s) {
	        if (!s.matches("(02|09)?-\\d{9}")) throw new IllegalArgumentException("Invalid phone number!");
	    }
}
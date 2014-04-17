package com.socialchef.service.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validateNames(String name) {
	//	Ejemplo: "Nombre Apellido Apellido "
	//	Ejemplo: "Nombre Apellido Apellido"
	//	Ejemplo: "Nombre Apellido"
	//	Ejemplo: "Apellido Apellido "
	//	Ejemplo: "Nombre"
	//	Ejemplo: "Apellido"
		Pattern regex = Pattern.compile("\\A([ña-zA-ZÑ]{3,16}+ {0,1}){1,3}+\\z");
		Matcher match = regex.matcher(name);
		return match.matches();
    }
    
    public static boolean validateUniqueNames(String unique_name) {
		Pattern regex = Pattern.compile("\\A\\w{4,10}\\z",
				Pattern.CASE_INSENSITIVE);
		Matcher match = regex.matcher(unique_name);
		return match.matches();
    }
    
    public static boolean validateEmail(String email) {
    	
    	String key = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*";
		String domain = "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    	Pattern regex = Pattern.compile(key+domain,
    			Pattern.CASE_INSENSITIVE);
		Matcher match = regex.matcher(email);
		return match.matches();
    }
}

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
	Pattern regex = Pattern.compile("\\A([ña-zA-z]{3,15}+\\s{0,1}){1,3}+\\z");
	Matcher match = regex.matcher(name);
	return match.matches();
    }
    
    public static boolean validateUniqueNames(String unique_name) {
	Pattern regex = Pattern.compile("\\A\\w{4,10}\\z");
	Matcher match = regex.matcher(unique_name);
	return match.matches();
    }
}

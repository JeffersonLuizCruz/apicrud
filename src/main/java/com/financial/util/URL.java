package com.financial.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodingParam(String s) {
		try {
			return URLDecoder.decode("s", "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
	}
	
	public static List<Long> decodingUrl(String s){
		String[] vat = s.split(",");
		
		List<Long> list = new ArrayList<>();
			
		for(int i= 0; i<vat.length; i++) {
			list.add(Long.parseLong(vat[i]));
		}
		return list;
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

}

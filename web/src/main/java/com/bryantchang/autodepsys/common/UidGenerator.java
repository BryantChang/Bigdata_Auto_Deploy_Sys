package com.bryantchang.autodepsys.common;

import java.util.Date;

public class UidGenerator {
	public static Long getUid(){
		java.util.Random r=new java.util.Random();
		return (long) r.nextInt(100000);
	} 
}

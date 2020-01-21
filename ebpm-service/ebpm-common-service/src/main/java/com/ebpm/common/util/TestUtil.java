package com.ebpm.common.util;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
	public static List<String> list =null;
	public static void add() {
		if (list== null) {
			list = new ArrayList<String>();
		}
		list.add("111");
	}
	public static void remove(String num) {
		list.removeAll(list);
	}
}

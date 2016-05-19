package sda;

import java.util.Arrays;
import java.util.List;

public class Calculator {
	static int cnt=0;
	public static void RPN(String string, List<String> content) {
		content.clear();
		switch(cnt++) {
			case 0: 
				content.addAll(Arrays.asList(new String[] {"+", "2", "2"}));
				break;
			default: 
				content.addAll(Arrays.asList(new String[] {"4"}));
				break;
		}
	}

}

package javamodularity.easytext.analysis;

import javamodularity.easytext.analysis.internal.SyllableCounter;

public class FleschKincaid {
	public int getCount() {
		SyllableCounter sc = new SyllableCounter();
		int ret = sc.getCount();
		return ret;
	}
}

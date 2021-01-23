package javamodularity.easytext.analysis.kincaid;

import javamodularity.easytext.analysis.api.Analyzer;

public class KincaidAnalyzer implements Analyzer {
	public int getCount() {
		return 2;
	}
	public String getName() {
		return "KincaidAnalyzer";
	}
}

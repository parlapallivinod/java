package javamodularity.easytext.analysis.coleman;

import javamodularity.easytext.analysis.api.Analyzer;

public class ColemanAnalyzer implements Analyzer {
	public int getCount() {
		return 1;
	}
	public String getName() {
		return "ColemanAnalyzer";
	}
}

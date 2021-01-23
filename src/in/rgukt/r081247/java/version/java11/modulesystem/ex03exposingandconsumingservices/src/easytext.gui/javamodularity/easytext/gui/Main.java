package javamodularity.easytext.gui;

import javamodularity.easytext.analysis.api.Analyzer;
import java.util.ServiceLoader;

public class Main {
	public static void main(String[] args) {
		Iterable<Analyzer> analyzers = ServiceLoader.load(Analyzer.class);
		System.out.println("From javamodularity.easytext.gui.Main");
		for (Analyzer analyzer: analyzers) {
			System.out.println(analyzer.getName() + ": " + analyzer.getCount());
		}
	}
}

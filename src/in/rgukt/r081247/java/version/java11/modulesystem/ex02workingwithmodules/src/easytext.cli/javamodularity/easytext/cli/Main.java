package javamodularity.easytext.cli;

import javamodularity.easytext.analysis.FleschKincaid;

public class Main {
	public static void main(String[] args) {
		FleschKincaid fk = new FleschKincaid();
		int ret = fk.getCount();
		System.out.println("From javamodularity.easytext.cli.Main");
		System.out.println("ret: " + ret);
	}
}

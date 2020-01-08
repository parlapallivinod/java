package in.rgukt.r081247.java.functionalprogramming;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class FP003_FileFilter {

    public static void anonymousInnerClass() {
        File file = new File("/");
        File[] files = file.listFiles(
                new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.isFile();
                    }
                }
        );

        Arrays.stream(files).map(f -> f.getName()).forEach(System.out::println);
    }

    public static void lamdaExpression() {
        File file = new File("/");
        File[] files = file.listFiles(f -> f.isFile());
        Arrays.stream(files).map(f -> f.getName()).forEach(System.out::println);
    }

    public static void main(String[] args) {
        //anonymousInnerClass();
        lamdaExpression();
    }
}

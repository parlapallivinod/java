package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

import java.io.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

class Solution {

    /*
     * Complete the 'anagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int anagram(String s) {
        // Write your code here
        if (s.length() % 2 != 0)
            return -1;
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        String s1 = s.substring(0, s.length()/2);
        String s2 = s.substring(s.length()/2);
        for (int i=0;i<s1.length();i++) {
            arr1[s1.charAt(i)-'a']++;
        }
        for (int i=0;i<s2.length();i++) {
            arr2[s2.charAt(i)-'a']++;
        }

        int count = 0;
        for (int i=0;i<arr1.length;i++) {
            if (arr1[i] > arr2[i]) {
                count = count + (arr1[i] - arr2[i]);
            }
        }

        return count;
    }

}

public class Anagram {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Solution.anagram(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

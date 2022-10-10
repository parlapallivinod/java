package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

public class ValidParentheses {
    /**
     * Approach   : Using Stack
     * Complexity : Time: O(n) ; Space: O(n)
     */
    class Solution1 {
        public boolean isValid(String s) {
            Map<Character, Character> map = Map.of('}', '{', ']', '[', ')', '(');
            Deque<Character> deque = new LinkedList<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(map.containsKey(c)) {
                    if(map.get(c) == deque.peek())
                        deque.pop();
                    else
                        return false;
                } else {
                    deque.push(c);
                }
            }
            return deque.isEmpty();
        }
    }
}

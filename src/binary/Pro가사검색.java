package binary;

import java.util.Arrays;
// 효울성 비통과
public class Pro가사검색 {
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        System.out.println(Arrays.toString( new Solution().solution(words, queries) ));
    }

    static class Solution {
        public int[] solution(String[] words, String[] queries) {
            int[] answer = new int[queries.length];
            // prefix surfix 구분
            // words를 돌며 길이와, query를 포함하는지 체크
            for(int i =0; i<queries.length; i++) {
                String query = queries[i];
                boolean prefix = false;
                if(query.charAt(0)=='?') prefix = true;
                int origin_len = query.length();
                query = query.replace("?", "");
                int cut_len = query.length();
                int cnt = 0;
                for (int j = 0; j < words.length; j++) {
                    if(words[j].length()!=origin_len) continue;
                    if(prefix){
                        if(words[j].substring(origin_len-cut_len, origin_len).equals(query)) cnt++;
                    }else{
                        if(words[j].substring(0, cut_len).equals(query)) cnt++;
                    }
                }
                answer[i] = cnt;
            }
            
            return answer;
        }
    }
}

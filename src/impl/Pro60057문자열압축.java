package impl;
// https://school.programmers.co.kr/learn/courses/30/lessons/60057
public class Pro60057문자열압축 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("abcabcabcabcdededededede"));
    }
    static class Solution {
        public int solution(String s) {
            int answer = 10001;
            // 무조건 unit갯수로 자름
            for (int unit = 1; unit <= s.length()/2+1; unit++) {// 테스트 5 : s가 1글자일때-unit 최대길이를 len/2+1로 설정
                String zipped = "";

                String prev = s.substring(0, unit);
                int cnt = 1;
                for (int j = unit; j < s.length(); j+=unit) {
                    int end = j+unit > s.length() ? s.length() : j+unit;
                    String next = s.substring(j, end);
                    if(next.equals(prev)){
                        cnt++;
                    }else{
                        zipped = cnt > 1 ? zipped+cnt+prev : zipped+prev;
                        prev = next;
                        cnt = 1;
                    }
                }
                // 끝 문자열 처리
                zipped = cnt > 1 ? zipped+cnt+prev : zipped+prev;
                
                answer = Math.min(zipped.length(), answer);
                System.out.println(zipped);
            }
            
            return answer;
        }
    }
}

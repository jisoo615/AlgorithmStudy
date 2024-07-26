package binary;

import java.util.*;

public class Pro가사검색_효율 {
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        System.out.println(Arrays.toString( new Pro가사검색_효율.Solution().solution(words, queries) ));
    }

    static class Solution {
        public int[] solution(String[] words, String[] queries) {
            int[] answer = new int[queries.length];
            // words를 길이 별로 나누어 담고, surfix를 위해 뒤집어진 문자열 준비
            HashMap<Integer, ArrayList<String>> data = new HashMap<>();
            HashMap<Integer, ArrayList<String>> reverse_data = new HashMap<>();
            for(String word : words){
                int len = word.length();
                ArrayList<String> list1 = data.getOrDefault(len, new ArrayList<>());
                list1.add(word);
                data.put(len, list1);
                ArrayList<String> list2 = reverse_data.getOrDefault(len, new ArrayList<>());
                list2.add(toReverseString(word));
                reverse_data.put(len, list2);
            }
            // 미리 정렬
            for(int key : data.keySet()){
                Collections.sort(data.get(key));
                Collections.sort(reverse_data.get(key));
            }
            
            for (int i = 0; i < queries.length; i++) {//lower, upper bound : 단어 first, last 위치 구해서 차이구하기
                int len = queries[i].length();
                ArrayList<String> list;// 길이에 맞는 찾을 데이터 가져오기
                String query_string;// 접두사, 접미사 구분 위해
                if(queries[i].charAt(0)=='?'){
                    list = reverse_data.get(len);
                    query_string = toReverseString(queries[i]);
                }else{
                    list = data.get(len);
                    query_string = queries[i];
                }

                if(list == null) continue;// 해당하는 길이의 데이터가 없을 경우 0
                
                String min_query = query_string.replace("?", "a");
                String max_query = query_string.replace("?", "z");

                int start = binarySearch(min_query, list);
                int end = binarySearch(max_query, list);

                answer[i] = end - start;
            }
            return answer;
        }

        static public int binarySearch(String str, ArrayList<String> list){// upper
            int left = 0, right = list.size();
            while (left < right){
                int mid = (left + right)/2;
                if(str.compareTo(list.get(mid)) >= 0){
                    left = mid+1;
                }else{
                    right = mid;
                }
            }
            return left;
        }

        static public String toReverseString(String str){
            StringBuilder sb = new StringBuilder();
            for (int i = str.length()-1; i >= 0; i--) {
                sb.append(str.charAt(i));
            }
            return sb.toString();
        }
    }
}

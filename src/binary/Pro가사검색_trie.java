package binary;

import java.util.Arrays;
import java.util.HashMap;
// https://school.programmers.co.kr/learn/courses/30/lessons/60060#
public class Pro가사검색_trie {
    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        System.out.println(Arrays.toString( new Pro가사검색_trie.Solution().solution(words, queries) ));
    }

    static class Solution {
        public int[] solution(String[] words, String[] queries) {
            int[] answer = new int[queries.length];
            Trie[] trie = new Trie[10001];// 길이별로 trie를 생성
            Trie[] reverse_trie = new Trie[10001];
            // 접미사-> trie, 접두사-> reverse_trie
            for(String word : words){
                int len = word.length();
                if(trie[len]==null){
                    trie[len] = new Trie();
                    reverse_trie[len] = new Trie();
                }
                StringBuilder sb = new StringBuilder(word);
                trie[len].insert(word);
                reverse_trie[len].insert(sb.reverse().toString());
            }

            int i = 0;
            for(String query : queries) {
                int len = query.length();
                if(trie[len]==null) continue;

                StringBuilder sb = new StringBuilder(query);
                int cnt = 0;
                if(query.charAt(0)=='?'){
                    cnt = reverse_trie[len].search(sb.reverse().toString());
                }else{
                    cnt = trie[len].search(query);
                }
                answer[i++] = cnt;
            }

            return answer;
        }

        static class Node{
            HashMap<Character, Node> children = new HashMap<>();
            boolean isLeaf = false;
            int childNo = 0;
        }

        static class Trie{
            Node rootNode = new Node();

            void insert(String str){
                Node parent = this.rootNode;
                for (int i = 0; i < str.length(); i++) {
                    parent.children.putIfAbsent(str.charAt(i), new Node());
                    parent.childNo++;// child가 있을떄마다 체크를 해주기 -> children.size로는 모름 chilNo없음 leaf까지 가서 개수를 다 세야함
                    parent = parent.children.get(str.charAt(i));
                }
                parent.isLeaf = true;// 마지막 노드 체크
            }

            int search(String str){
                Node node = this.rootNode;
                int cnt = 0;
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if(c=='?') break;
                    if(!node.children.containsKey(c)) return 0;
                    node = node.children.get(c);
                }
                return node.childNo;
            }
        }
    }
}

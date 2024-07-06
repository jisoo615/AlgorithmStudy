package sort;

import java.util.ArrayList;
import java.util.Arrays;

public class Pro42889실패율 {
    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        System.out.println(Arrays.toString(new Solution().solution(N, stages)));// [3,4,2,1,5]
    }
    static class Solution {
        public int[] solution(int N, int[] stages) {
            int[] answer = new int[N];

            int[] cnt = new int[N+2];
            Arrays.sort(stages);
            int[] tmp = new int[N+2];
            for(int stage : stages){
                cnt[stage]++;
                tmp[stage]++;
            }
            for (int i = N; i > 0; i--) {
                tmp[i] += tmp[i+1];
            }
            ArrayList<Info> list = new ArrayList<>();
            for (int i = 1; i < N+ 1; i++) {
                double fail_rate;
                if(cnt[i]==0 || tmp[i]==0) fail_rate = 0;
                else fail_rate= (double)cnt[i]/(double) tmp[i];
                list.add(new Info(i, fail_rate));
            }
            list.sort((f1, f2)->{
                if(f2.fail_rate == f1.fail_rate) return f1.level - f2.level;
                else if(f2.fail_rate < f1.fail_rate) return -1;
                else return 1;
            });
            for (int i = 0; i < N; i++) {
                answer[i] = list.get(i).level;
            }

            return answer;
        }
    }
    static class Info{
        int level;
        double fail_rate;
        public Info(int level, double fail_rate) {this.level = level; this.fail_rate = fail_rate;}
    }
}

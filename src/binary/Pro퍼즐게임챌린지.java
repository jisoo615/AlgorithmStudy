package binary;
// https://school.programmers.co.kr/learn/courses/30/lessons/340212?language=java
public class Pro퍼즐게임챌린지 {

    static class Solution {
        public int solution(int[] diffs, int[] times, long limit) {

            int end = 100001;// 레벨
            int start = 1;// 레벨

            while(start <= end){
                int mid = (end+start)/2;
                if(calcPuzzleTime(diffs, times, mid) > limit){// 소요시간 초과되면 레벨 올려야됨
                    start = mid + 1;
                }else end = mid-1;

            }
            return start;
        }

        public long calcPuzzleTime(int[] diffs, int[] times, int level){
            long sum = 0;
            for (int i = 0; i < diffs.length; i++) {
                if(diffs[i] <= level){
                    sum += times[i];
                    continue;
                }
                sum += (long) (times[i] + times[i - 1]) * (diffs[i] - level) + times[i];
            }
            return sum;
        }
    }
}

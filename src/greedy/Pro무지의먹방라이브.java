package greedy;

import java.util.ArrayList;
import java.util.PriorityQueue;

//https://school.programmers.co.kr/learn/courses/30/lessons/42891
// 효울성 테스트
public class Pro무지의먹방라이브 {

    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        long k = 5;
        System.out.println(new Solution().solution(food_times, k));
    }

    static class Solution {
        public int solution(int[] food_times, long k) {
            int answer = 0;

            PriorityQueue<Food> pq = new PriorityQueue<Food>(
                    (f1, f2)->{
                        if(f1.time == f2.time){
                            return f1.num - f2.num;
                        }
                        if(f1.time < f2.time)
                            return -1;
                        else return 1;
                    }
            );
            for (int i = 0; i < food_times.length; i++) {
                pq.add(new Food(i+1, food_times[i]));
            }

            long sum = 0;
            while ( sum + pq.peek().time < k ){
                Food food = pq.poll();
                sum += food.time;
            }
            // 남은 시간 번째의 음식을 찾기
            ArrayList<Food> list = new ArrayList<>();

            if(pq.isEmpty()) return -1;

            while (!pq.isEmpty()){
                list.add(pq.poll());
            }
            list.sort((f1, f2) -> f1.num - f2.num);
            int index = (int) (k-sum) % list.size();
            return list.get(index).num;
        }
    }

    static class Food{
        int num;
        long time;
        public Food(int num, long time){
            this.num = num;
            this.time = time;
        }
    }
}

package impl;

import java.util.Arrays;

public class Pro외벽점검 {
    public static void main(String[] args) {
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};// answer 2
        System.out.println(new Solution().solution(12, weak, dist));

    }
    static class Solution{
        static int min = Integer.MAX_VALUE;
        static int[] weakArray;
        static int[] dist;

        public int solution(int n, int[] weak, int[] dist) {
            // 반시계방향 위해 weak 두배로
            this.dist = dist;
            weakArray = new int[weak.length*2];
            for (int i = 0; i <weak.length; i++) {
                weakArray[i] = weak[i];
                weakArray[i+weak.length] = weak[i]+n;
            }

// dist를 순열로 만들어 weakArray를 0~weak.len 을 다 커버하는지 확인하기
            for (int i = 0; i < weak.length; i++) {// weak를 시작할 시작점
                dfs(i, 0, new boolean[dist.length], new int[dist.length]);
            }

            if(min==Integer.MAX_VALUE) return -1;
            return min;
        }

        public void dfs(int start, int depth, boolean[] visited, int[] permuted){
            if(depth==permuted.length){
                min = Math.min(min, check(start, permuted));
                return;
            }
            for (int i = 0; i < permuted.length; i++) {
                if(visited[i]) continue;
                visited[i] = true;
                permuted[depth] = dist[i];
                dfs(start, depth+1, visited, permuted);
                visited[i] = false;
            }
        }

        public int check(int start, int[] permuted){
            int end = start + weakArray.length/2;
            int friend = 1;
            int position = weakArray[start] + permuted[friend-1];// 첫번째 친구가 커버하는 곳

            for (int i = start; i < end; i++) {
                if(position >= weakArray[i]) continue;
                friend++;
                if(friend > permuted.length) return Integer.MAX_VALUE;
                position = weakArray[i] + permuted[friend-1];
            }
            System.out.println(Arrays.toString(permuted)+" "+friend);
            return friend;
        }
    }
}

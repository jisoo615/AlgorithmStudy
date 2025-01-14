package bfs_dfs;

import java.util.HashMap;
import java.util.Objects;

// https://school.programmers.co.kr/learn/courses/30/lessons/340211
public class Pro충돌위험찾기 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[][] {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}},
                new int[][] {{2, 3, 4, 5}, {1, 3, 4, 5}}
                ));
    }

    static class Solution {
        public int solution(int[][] points, int[][] routes) {
            int answer = 0;

            HashMap<Integer, Point> pointMap = new HashMap<Integer, Point>();
            for(int i = 0; i < points.length; i++){
                pointMap.put(i+1, new Point(points[i][0]-1, points[i][1]-1));
            }

            // routes는 로봇이 방문하는 point 순서대로 구성
            // x를 먼저 움직임-> x가 같으면 y를 움직이도록 하기만 하면 되니까 bfs로 안해도 될듯 : 무조건 ㄴ ㅡ ㅣ 길만 나옴
            // 충돌을 어떻게 측정할지-> 다른 초에 같은 지점이 충돌나면 그것도 추가해야함
            // 초마다 해당 좌표에 있는 로봇을 세기 map<초, Map<Point, Integer>>
            HashMap<Integer, HashMap<Point, Integer>> conflictMap = new HashMap<Integer, HashMap<Point, Integer>>();

            for(int[] route : routes){
                int second = 0;

                // 첫 시작 지점 0초
                Point first = pointMap.get(route[0]);
                HashMap<Point, Integer> innerMap1 = conflictMap.computeIfAbsent(second, k -> new HashMap<>());
                innerMap1.put(new Point(first.x, first.y), innerMap1.getOrDefault(new Point(first.x, first.y), 0) + 1);

                for (int i = 0; i < route.length-1; i++) {
                    Point start = new Point(pointMap.get(route[i]).x, pointMap.get(route[i]).y);
                    Point end = new Point(pointMap.get(route[i+1]).x, pointMap.get(route[i+1]).y);

                    while(start.x != end.x || start.y != end.y){
                        if(start.x < end.x) start.x += 1;
                        else if(start.x > end.x) start.x -= 1;
                        else if(start.y < end.y) start.y += 1;
                        else if(start.y > end.y) start.y -= 1;
                        second++;
                        HashMap<Point, Integer> innerMap2 = conflictMap.computeIfAbsent(second, k -> new HashMap<>());
                        innerMap2.put(new Point(start.x, start.y), innerMap2.getOrDefault(new Point(start.x, start.y), 0) + 1);
                    }
                }
            }

            for (int i = 0; i < conflictMap.size(); i++) {
                for( Point p : conflictMap.get(i).keySet()){
                    if(conflictMap.get(i).get(p) >= 2) answer++;
                }
            }
            
            return answer;
        }

        static class Point {
            int x, y;
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Point point = (Point) o;
                return x == point.x && y == point.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }

        }
    }

}

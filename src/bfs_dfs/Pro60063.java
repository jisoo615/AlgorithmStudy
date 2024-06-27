package bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

//블록 이동하기 https://school.programmers.co.kr/learn/courses/30/lessons/60063
public class Pro60063 {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};// 7
        System.out.println(new Solution().solution(board));
    }

    static class Solution {
        static int N;
        static int[][] board;
        public int solution(int[][] board) {
            //bfs 하, 우, 회전방향(회전방향 4칸이 다 비어있어야 가능)
            int min = Integer.MAX_VALUE;
            N = board.length;
            this.board = board;

            Queue<Robot> q = new LinkedList<Robot>();
            Robot start = new Robot(0, 0, 0, 1, 0);
            q.add(start);
            while (!q.isEmpty()){
                Robot r = q.poll();

                // 종료조건
                if(r.lx==N-1 && r.ly==N-1) min = Math.min(min, r.time);
                if(r.rx==N-1 && r.ry==N-1) min = Math.min(min, r.time);

                if(inbound(r.lx+1, r.ly, r.rx+1, r.ry)){// 하
                    q.add(new Robot(r.lx+1, r.ly, r.rx+1, r.ry, r.time+1 ));
                }
                if(inbound(r.lx, r.ly+1, r.rx, r.ry+1)){// 우
                    q.add(new Robot(r.lx, r.ly+1, r.rx, r.ry+1, r.time+1 ));
                }
                Robot rotated = rotate(r);// 회전
                if(rotated != null) q.add(rotated);
            }

            return min;
        }

        static boolean inbound(int lx, int ly, int rx, int ry){
            if(lx<0 || lx>=N || ly<0 || ly>=N || rx<0 || rx>=N || ry<0 || ry>=N) return false;
            if(board[lx][ly] == 1 || board[rx][ry] == 1) return false;// 벽 경우
            return true;
        }
        static Robot rotate(Robot r){
            // 2x2 공간 확보, 회전된 로봇 반환
            if(r.lx==r.rx){// 가로상태 - 반시계방향
                if(inbound(r.lx+1, r.ly, r.rx+1, r.ry)){
                    return new Robot(r.lx+1, r.ly+1, r.rx, r.ry, r.time+1);
                }
            }else{// 세로상태 - 시계방향
                if(inbound(r.lx, r.ly+1, r.rx, r.ry+1)){
                    return new Robot(r.lx, r.ly, r.rx+1, r.ry+1, r.time+1);// 가로(LR)->세로("R"/L)->"가로(RL)"
                }
            }
            return null;
        }

        static class Robot{
            int lx, ly, rx, ry;
            int time;
            public Robot(int lx, int ly, int rx, int ry, int time) {
                this.lx = lx; this.ly = ly; this.rx = rx; this.ry = ry;
                this.time = time;
            }
        }
    }
}

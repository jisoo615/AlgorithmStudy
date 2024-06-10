package impl;

import java.util.ArrayList;
import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/60061
public class Pro60061 {
    public static void main(String[] args) {
        int[][] build_frame= {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
        System.out.println(Arrays.deepToString(Solution.solution(5, build_frame)));
    }
    static class Solution{
        static boolean[][][] installed;
        static int N;

        public static int[][] solution(int n, int[][] build_frame) {
            N = n+1;
            installed = new boolean[N][N][2];// [][][0]:기둥 [][][1]:보
            for(int[] frame : build_frame){
                int x = frame[0], y = frame[1], type = frame[2], isInstall = frame[3];

                if(type==0){// 기둥 아래가 바닥/기둥, 한쪽이 보
                    if(isInstall == 1){// 설치
                        if(confirmColumn(x, y)) installed[x][y][0]=true;
                    }else{//삭제
                        installed[x][y][0] = false;
                        if(
                           (isColumn(x, y+1)&&!confirmColumn(x, y+1)) ||
                           (isCarpet(x, y+1)&&!confirmCarpet(x, y+1)) ||
                           (isCarpet(x-1, y+1)&&!confirmCarpet(x-1, y+1))
                        ){
                            installed[x][y][0] = true;
                        }
                    }
                }else{// 보 한쪽 기둥, 양쪽 보
                    if(isInstall == 1){// 설치
                        if(confirmCarpet(x, y)) installed[x][y][1] = true;
                    }else{//삭제
                        installed[x][y][1] = false;
                        if(
                           (isCarpet(x-1, y)&&!confirmCarpet(x-1, y)) ||
                           (isCarpet(x+1, y)&&!confirmCarpet(x+1, y)) ||
                           (isColumn(x, y)&&!confirmColumn(x, y)) ||
                           (isColumn(x+1, y)&&!confirmColumn(x+1, y))
                        ){
                            installed[x][y][1] = true;
                        }
                    }
                }

            }

            ArrayList<int[]> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(installed[i][j][0]) list.add(new int[] {i, j, 0});
                    if(installed[i][j][1]) list.add(new int[] {i, j, 1});
                }
            }
            list.sort((int[] s1, int[] s2) -> {
                if(s1[0] == s2[0]){
                    if(s1[1]==s2[1]){
                        return s1[2] - s2[2];
                    }
                    return s1[1] - s2[1];
                }
                return s1[0] - s2[0];
            });
            int[][] result = new int[list.size()][3];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }

            return result;
        }

        public static boolean isCarpet(int x, int y){
            if(x<0||x> N || y<0||y> N) return false;
            return installed[x][y][1];
        }
        public static boolean isColumn(int x, int y){
            if(x<0||x> N || y<0||y> N) return false;
            return installed[x][y][0];
        }
        public static boolean confirmColumn(int x, int y){
            return y==0 || isColumn(x, y-1) || isCarpet(x-1, y) || isCarpet(x, y);
        }
        public static boolean confirmCarpet(int x, int y){
            return (isCarpet(x-1, y) && isCarpet(x+1, y)) ||
                    isColumn(x, y-1) || isColumn(x+1, y-1);
        }
    }
}

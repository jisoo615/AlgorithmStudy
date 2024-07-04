package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Student> students = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            students.add(
                    new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
            );
        }
        students.sort((s1, s2)->{
            if(s2.korean == s1.korean){
                if(s1.english == s2.english){
                    if(s1.math == s2.math){
                        return s1.name.compareTo(s2.name);
                    }
                    return s2.math - s1.math;
                }
                return s1.english - s2.english;
            }
            return s2.korean - s1.korean;
        });
        StringBuilder sb = new StringBuilder();
        for(Student student : students) sb.append(student.name+"\n");
        System.out.println(sb);
    }
    static class Student{
        String name;
        int korean, english, math;
        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.english = english;
            this.korean = korean;
            this.math = math;
        }
    }
}

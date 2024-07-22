package impl;

import java.util.ArrayList;

public class P8문자재정렬 {
    public static void main(String[] args) {
        String input = "K1KA5CB7";
        String output = "ABCKK13";
        ArrayList<String> arrayList = new ArrayList<>();
        int num = 0;
        String[] arr = input.split("");
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].matches("[A-Z]")){
                arrayList.add(arr[i]);
            }
            else num += Integer.parseInt(arr[i]);
        }

        arrayList.sort((s1, s2)-> s1.compareTo(s2));
        StringBuilder sb = new StringBuilder();
        for(String str : arrayList) sb.append(str);
        sb.append(num);

        System.out.println("입력 : "+input);
        System.out.println("출력 : "+sb.toString());
        System.out.println("정답 : "+output);
    }
}

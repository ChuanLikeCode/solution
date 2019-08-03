package zjtd1;

import java.util.*;

//输入例子1:
//    5
//    1 2 3 3 5
//    3
//    1 2 1
//    2 4 5
//    3 5 3
//
//    输出例子1:
//    1
//    0
//    2
//
//    例子说明1:
//    样例解释:
//    有5个用户，喜好值为分别为1、2、3、3、5，
//    第一组询问对于标号[1,2]的用户喜好值为1的用户的个数是1
//    第二组询问对于标号[2,4]的用户喜好值为5的用户的个数是0
//    第三组询问对于标号[3,5]的用户喜好值为3的用户的个数是2
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int n = Integer.parseInt(sc.nextLine());
            String[] str = sc.nextLine().split(" ");
            int askCount = Integer.parseInt(sc.nextLine());
            HashMap<Integer,List<Integer>> map = new HashMap<>();
            for (int i = 0;i<n;i++){
                if (map.containsKey(Integer.parseInt(str[i]))){
                    map.get(Integer.parseInt(str[i])).add(i+1);
                }else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i+1);
                    map.put(Integer.parseInt(str[i]),list);
                }
            }
            while (askCount > 0){
                String[] ask = sc.nextLine().split(" ");
                int l = Integer.parseInt(ask[0]);
                int r = Integer.parseInt(ask[1]);
                int k = Integer.parseInt(ask[2]);
                int count=0;
                if (map.containsKey(k)){
                    List<Integer> list = map.get(k);
                    for (Integer num:list){
                        if (num>=l&&num<=r){
                            count++;
                        }
                    }
                }
                System.out.println(count);
                askCount--;
            }
        }
    }
}
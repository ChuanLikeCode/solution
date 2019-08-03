package didi2;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        List<Person> list = new ArrayList<>();
        list.add(new Person("2019-06-28 上午",1));
        list.add(new Person("2019-06-27 上午",2));
        list.add(new Person("2019-06-28 下午",3));
        list.add(new Person("2019-06-24 上午",4));
        list.add(new Person("2019-06-24 下午",5));
        System.out.println(ResultVOUtil.success(list).getCount());
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
//            String[] nm = sc.nextLine().split(",");
//            int n = Integer.parseInt(nm[0]);//桌子
//            int m = Integer.parseInt(nm[1]);//m批客人
//            String[] astr = sc.nextLine().split(" ");
//            int[] a = new int[n];
//            for (int i = 0;i<n;i++){
//                a[i] = Integer.parseInt(astr[i]);
//            }
//            int[] b = new int[m];
//            int[] c = new int[m];
//            for (int i=0;i<m;i++){
//                String[] str = sc.nextLine().split(",");
//                b[i] = Integer.parseInt(str[0]);
//                c[i] = Integer.parseInt(str[1]);
//            }
//            Arrays.sort(a);
//            int sum =0;
//
//        }
    }

    static int getMaxIndex(int[] c){
        int max = 0;
        int index =0;
        for (int i =0;i<c.length;i++){
            if (max <= c[i]){
                max = c[i];
                index = i;
            }
        }
        return index;
    }
}
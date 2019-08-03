package yuanfudao;

import java.util.Scanner;
//某天猿辅导 HR 组织大家去漂流，
// 早上，参加团建的同学都到齐了，并且按到达公司的先后顺序排好队了。
// 由于员工太多，一个大巴车坐不下，需要分多个车，车是足够的，
// 但所有人需要按一定顺序上车，按如下规则安排上车的顺序：
//        假设大巴车容量为 m，从队首开始，每 m 个人分成一个小组，
// 每个小组坐一辆车。同时只有一个车打开车门供员工上车。
// 小组之间按从队尾到队首顺序依次上车，同一小组内先到的同学先上，求所有人上车的顺序。
//
//        例如： 员工数 8， 车容量 3， 员工到达顺序为 1 2 3 4 5 6 7 8，
// 3个人一个小组，分三个小组， 小组一： 1， 2， 3， 小组二： 4， 5， 6，小组三： 7，8。
// 小组上车顺序为： 小组三，小组二，小组一 。 所有员工上车顺序为 7 8 4 5 6 1 2 3

//输入描述:
//        第一行： 员工数和大巴容量
//        第二行： 所有员工工号（按到达顺序）
//
//        输出描述:
//        员工编号
//
//        输入例子1:
//        5 3
//        1 3 5 2 4
//
//        输出例子1:
//        2 4 1 3 5
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0;i<nums.length;i++){
            nums[i] = scanner.nextInt();
        }
        int zu = n/m;
        int yu = n%m;
        if (yu!=0)
            zu++;
        while (zu != 0){
            zu--;
            int start = zu * m;
            int end = start+m >= n?n:start+m;
            for (int i = start;i<end;i++){
                System.out.print(nums[i]+" ");
            }
        }
    }
}

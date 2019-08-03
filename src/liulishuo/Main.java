/**
 * ////////////////////////////////////////////////////////////////////
 * //                          _ooOoo_                               //
 * //                         o8888888o                              //
 * //                         88" . "88                              //
 * //                         (| ^_^ |)                              //
 * //                         O\  =  /O                              //
 * //                      ____/`---'\____                           //
 * //                    .'  \\|     |//  `.                         //
 * //                   /  \\|||  :  |||//  \                        //
 * //                  /  _||||| -:- |||||-  \                       //
 * //                  |   | \\\  -  /// |   |                       //
 * //                  | \_|  ''\---/''  |   |                       //
 * //                  \  .-\__  `-`  ___/-. /                       //
 * //                ___`. .'  /--.--\  `. . ___                     //
 * //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
 * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
 * //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
 * //      ========`-.____`-.___\_____/___.-`____.-'========         //
 * //                           `=---='                              //
 * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
 * //            佛祖保佑       永不宕机     永无BUG                    //
 * ////////////////////////////////////////////////////////////////////
 *
 * @author zhouchuan
 * @date 2019/7/30 18:30
 * email:zc19930508@163.com
 * QQ:461346605
 * Tel:13218329781
 * If you have any question,please call me.
 **/
package liulishuo;
import java.util.*;

/**
 *@author zhouchuan
 *@date 2019/7/30 18:30
 *email:zc19930508@163.com
 *QQ:461346605 
 *Tel:13218329781
 *If you have any question,please call me.
 */
public class Main {
    /**
     * 设计一种编码长度最短
     * 哈夫曼编码
     */
    static class HuffmanNode{
        int weight;
        HuffmanNode left;
        HuffmanNode right;
        Character ch;
        public HuffmanNode(int weight){
            super();
            this.weight = weight;
        }
        public HuffmanNode(int weight,char ch){
            super();
            this.weight = weight;
            this.ch = ch;
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();
            System.out.println(createHuffman(input));
        }

    }

    private static int createHuffman(String s) {
        char[] chars = s.toCharArray();
        Map<Character,Integer> map = new  HashMap<>();
        for (int i = 0;i<chars.length;i++){
            if (map.containsKey(chars[i])){
                map.put(chars[i],map.get(chars[i])+1);
            }else {
                map.put(chars[i],1);
            }
        }
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(map.size(), new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode o1, HuffmanNode o2) {
                return o1.weight-o2.weight;
            }
        });
        for (Map.Entry<Character,Integer> entry:map.entrySet()){
            queue.offer(new HuffmanNode(entry.getValue(),entry.getKey()));
        }
        while (queue.size()>1){
            HuffmanNode leftNode = queue.poll();
            HuffmanNode rightNode = queue.poll();
            HuffmanNode fatherNode = new HuffmanNode(leftNode.weight+rightNode.weight);
            fatherNode.left = leftNode;
            fatherNode.right = rightNode;
            queue.offer(fatherNode);
        }
        HuffmanNode root = queue.poll();
        return getLength(root,0);
    }

    private static int getLength(HuffmanNode node, int depth) {
        if (node==null){
            return 0;
        }
        return (node.ch == null?0:node.weight)*depth+getLength(node.left,depth+1)+getLength(node.right,depth+1);
    }
}

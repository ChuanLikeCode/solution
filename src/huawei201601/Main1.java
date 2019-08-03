package huawei201601;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
//        处理:
//        1.记录最多8条错误记录，对相同的错误记录(即文件名称和行号完全匹配)只记录一条，错误计数增加；(文件所在的目录不同，文件名和行号相同也要合并)
//        2.超过16个字符的文件名称，只记录文件的最后有效16个字符；(如果文件名不同，而只是文件名的后16个字符和行号相同，也不要合并)
//        3.输入的文件可能带路径，记录文件名称不能带路径
//
//        输入描述:
//        一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开。
//
//        文件路径为windows格式
//
//        如：E:\V1R2\product\fpgadrive.c 1325
//
//        输出描述:
//        将所有的记录统计并将结果输出，格式：文件名代码行数数目，一个空格隔开，如: fpgadrive.c 1325 1
//
//        结果根据数目从多到少排序，数目相同的情况下，按照输入第一次出现顺序排序。
//
//        如果超过8条记录，则只输出前8条记录.
//
//        如果文件名的长度超过16个字符，则只输出后16个字符
//
//        输入例子1:
//        E:\V1R2\product\fpgadrive.c 1325
//
//        输出例子1:
//        fpgadrive.c 1325 1
public class Main1 {

    static class Node implements Comparable<Node>{
        public String fileName;
        public String colNum;
        public int total;
        public int index;

        public Node(String fileName, String colNum, int total,int index) {
            this.fileName = fileName;
            this.colNum = colNum;
            this.total = total;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if (this.total < o.total )
                return 1;
            else if (this.total>o.total)
                return -1;
            else {
                if (this.index < o.index)
                    return -1;
                else if (this.index > o.index)
                    return 1;
                else
                    return 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        Map<String,Node> map = new HashMap<>();
        FileReader fr = new FileReader("C:\\Users\\user\\IdeaProjects\\solution\\src\\huawei201601\\test.txt");
        BufferedReader bf = new BufferedReader(fr);
        String line = "";
        int index = 0;
        while ((line = bf.readLine())!=null){
            String[] input = line.split(" ");
            String[] fileName = input[0].split("\\\\");
            int length = fileName.length-1;
            if (!map.containsKey(fileName[length]+input[1])){
                map.put(fileName[length]+input[1],new Node(fileName[length],input[1],1,index));
            }else {
                Node node = map.get(fileName[length]+input[1]);
                node.total++;
            }
            index++;
        }
//        while (sc.hasNextLine()) {
//            String[] input = sc.nextLine().split(" ");
//            String[] fileName = input[0].split("\\\\");
//            int length = fileName.length-1;
//            int index = 0;
//            if (!map.containsKey(fileName[length]+input[1])){
//                map.put(fileName[length]+input[1],new Node(fileName[length],input[1],1,index));
//            }else {
//                Node node = map.get(fileName[length]+input[1]);
//                node.total++;
//            }
//            index++;
//        }
        Set<String> strings = map.keySet();
        List<Node> list = new ArrayList<>();
        for (String key:strings){
            list.add(map.get(key));
        }
        Collections.sort(list);
        int total;
        if (list.size() >= 8){
            total = 8;
        }else {
            total = list.size();
        }

        for (int i = 0;i<total;i++){
            String filename = list.get(i).fileName;
            if (filename.length() > 16)
                System.out.println(filename.substring(filename.length()-16,filename.length())+" "+list.get(i).colNum+" "+list.get(i).total);
            else
                System.out.println(filename+" "+list.get(i).colNum+" "+list.get(i).total);
        }
    }
}

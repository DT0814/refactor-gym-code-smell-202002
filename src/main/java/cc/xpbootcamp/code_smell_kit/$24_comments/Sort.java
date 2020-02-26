package cc.xpbootcamp.code_smell_kit.$24_comments;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] arr={5,4,6,2,1,7,0,9,8,3};
        int[] res = sort(arr);
        System.out.println(Arrays.toString(res));
    }

    public static int[] sort(int[] data){ //没有必要的注释
        int k = 0;//选择指针
        int tmp = 0;//用于交换位置的临时变量
        for (int i = 0; i < data.length - 1; i++) {
            //遍历第一个到倒数第二个元素
            k = i;//假设索引i的数是最小值
            for(int j = i + 1; j < data.length; j++) {
                //遍历第二到最后一个元素
                if (data[j] < data[k]) {
                    //如果索引j更小,说明k不是最小,要改
                    k=j;
                }
                //这个循环结束的时候k就是最小值的索引
            }
            //依次找出最小的值所在位置k,与从0开始遍历数组的元素交换
            if (k != i){
                tmp = data[k];
                data[k] = data[i];
                data[i] = tmp;
            }
        }
        return data;
    }
}

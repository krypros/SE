package applications;
/*
* 2022/9/9 https://leetcode.cn/problems/crawler-log-folder/submissions/
* 判断String是否相等
* ==判断字符串时，判断的是两个字符串是否指向同一个对象
* equals() 先判断引用指向对象是否相同，再判断字符是否相同
* */
class crawlerLogFolder {
    public static void main(String[] args) {
        String[] logs = {"d1/","d2/","../","d21/","./"};
        int res = new crawlerLogFolder().minOperations(logs);
        System.out.println(res);
    }
    public int minOperations(String[] logs) {
        int len = 0;
        int prev = 0;
        while(prev < logs.length) {
            if("../".equals(logs[prev])) {
                len = len == 0 ? 0 : len - 1;
            } else if(!"./".equals(logs[prev])) {
                len += 1;
            }
            prev += 1;
        }
        return len;
    }
}

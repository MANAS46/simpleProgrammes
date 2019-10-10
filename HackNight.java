import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HackNight {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while (t-- > 0) {
            String str = sc.nextLine();
            String s1 = str.split(" ")[0];
            String s2 = str.split(" ")[1];
            if (s1.equals(s2)) {
                System.out.println(0);
                continue;
            }
            List<Set> res1 = getResult(s1);
            List<Set> res2 = getResult(s2);
            if (res1.size() != res2.size()) {
                System.out.println(-1);
            } else {
                boolean flag = false;
                int count = 0;
                for (int i = 0; i < res1.size(); ++i) {
                    if (res1.get(i).c == res2.get(i).c) {
                        int cnt = res1.get(i).i - res2.get(i).i;
                        if (cnt < 0) {
                            cnt *= -1;
                        }
                        count += cnt;
                    } else {
                        System.out.println(-1);
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    continue;
                System.out.println(count);
            }
        }
    }

    private static List<Set> getResult(String s) {
        List<Set> res = new ArrayList<>();
        char[] array = s.toCharArray();
        char prev = array[0];
        res.add(new Set(prev, 1));
        for (int i = 1; i < array.length; ++i) {
            if (prev == array[i]) {
                int size = res.size();
                int count = res.get(size - 1).i;
                res.remove(size - 1);
                res.add(new Set(array[i], ++count));
            } else {
                prev = array[i];
                res.add(new Set(array[i], 1));
            }
        }
        return res;
    }

    private static class Set {
        private char c;
        private int i;

        public Set(char c, int i) {
            this.c = c;
            this.i = i;
        }
    }

}

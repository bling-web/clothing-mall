public class Test {
    public static void main(String[] args) {
        String s="1,2,3";
        String[] split = s.split(",");
        for (String s1 : split) {
            System.out.println(s1);
        }

    }
}

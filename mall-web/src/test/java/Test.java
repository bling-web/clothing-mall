import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        String total_amount="23.00";
        if(total_amount.charAt(total_amount.length()-1)=='0'){
            total_amount=total_amount.substring(0,total_amount.length()-1);
        }
        System.out.println(total_amount);

    }
}

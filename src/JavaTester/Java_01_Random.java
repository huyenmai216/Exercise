package JavaTester;

import java.util.Random;

public class Java_01_Random {
    //Java Builtin (cung cấp sẵn - lấy ra sử dụng)
    // Java Libraries ( Do 1 cá nhân, tổ chức tự viết)

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt());
        System.out.println(rand.nextInt());
        System.out.println(rand.nextInt());
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextDouble());
        // random int không chứa số âm
        System.out.println(rand.nextInt(99999));


        // giá trị email mong muốn sẽ là automation1234.com





    }
}

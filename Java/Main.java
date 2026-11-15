import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // write the numbers 1..n in a row
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        int size = n;
        int step = 0; // 0 is removing even positions, 1 is removing odd positions, 2 is removing positions divisible by 3

        while (size > 1) {
            int newSize = 0;

            for (int i = 0; i < size; i++) {
                int pos = i + 1;
                boolean remove;

                if (step == 0) {
                    remove = pos % 2 == 0;
                } 
                else if (step == 1) {
                    remove = pos % 2 == 1;
                } 
                else {
                    remove = pos % 3 == 0;
                }

                if (!remove) {
                    numbers[newSize] = numbers[i];
                    newSize++;
                }
            }

            size = newSize;
            step = (step + 1) % 3;
        }
        System.out.println(numbers[0]);
    }
}
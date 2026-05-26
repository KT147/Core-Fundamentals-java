package YahtzeeGame;

import java.util.*;
import java.util.stream.IntStream;

public class Game {

    public static void main(String[] args) {
        playGame();

    }

    public static Integer[] startTheGame() {
        Random random = new Random();

        Integer[] dice = random.ints(1, 7)
                .limit(5)
                .boxed()
                .toArray(Integer[]::new);


        System.out.println("Your dice are " + Arrays.toString(dice));

        return dice;
    }

    public static void playGame() {
        Integer[] dice = startTheGame();

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        int rollsCount = 0;

        while (flag && rollsCount < 5) {
            System.out.println("Press Enter to Score");
            System.out.println("Type ALL to re-roll all the dice");
            System.out.println("List numbers with spaces to re roll selected dice");
            var response = scanner.nextLine();

            if (response.isEmpty()) {
                flag = false;
                System.out.println("Game Over");
            }
            else if (response.equalsIgnoreCase("ALL")) {
                dice = startTheGame();
                rollsCount++;
            }
            else if (response.matches("[1-5 ]+")) {
                String[] parts = response.split(" ");

                for (String part : parts) {
                    switch (part) {
                        case "1" -> dice[0] = getNewRandom();
                        case "2" -> dice[1] = getNewRandom();
                        case "3" -> dice[2] = getNewRandom();
                        case "4" -> dice[3] = getNewRandom();
                        case "5" -> dice[4] = getNewRandom();
                    }
                }
                rollsCount++;
                System.out.println("Your dice are " + Arrays.toString(dice));
            } else {
                flag = false;
                System.out.println("Game Over");
            }
        }
    }

    public static int getNewRandom() {
        Random random = new Random();
        return random.nextInt(1,7);
    }
}

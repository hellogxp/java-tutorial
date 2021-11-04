package comparator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chopin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/4 14:11
 */
public class PlayerAgeSorting {
    public static void main(String[] args) {
        List<Player> playerList = new LinkedList<>();
        Player player1 = new Player(40, "Mark", 42);
        Player player2 = new Player(50, "David", 45);
        Player player3 = new Player(30, "Ben", 32);

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        playerList.forEach(player -> System.out.printf("%s", player.getAge() + ":" + player.getName() + "\n"));
        PlayAgeComparator playAgeComparator = new PlayAgeComparator();
        playerList.sort(playAgeComparator);
        System.out.println("After sorting");
        playerList.forEach(player -> System.out.printf("%s", player.getAge() + ":" + player.getName() + "\n"));
    }
}
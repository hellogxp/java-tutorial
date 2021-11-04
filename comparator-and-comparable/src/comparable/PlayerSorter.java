package comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chopin
 * @version 1.0
 * @description: TODO
 * @date 2021/10/28 13:38
 */
public class PlayerSorter {
    public static void main(String[] args) {
        List<Player> playerList = new ArrayList<Player>();
        Player player1 = new Player(59, "Ben", 32);
        Player player2 = new Player(65, "Mark", 42);
        Player player3 = new Player(30, "Olive", 30);
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        playerList.forEach(player -> System.out.printf("%s", player.getName() + " : " + player.getAge() + "\n"));
        Collections.sort(playerList);
        //System.out.println("After Sorting : " + playerList);
        System.out.println("After Sorting");
        playerList.forEach(player -> System.out.printf("%s", player.getName() + " : " + player.getAge() + "\n"));
    }
}
package comparator;

import java.util.Comparator;

/**
 * @author chopin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/4 14:09
 */
public class PlayAgeComparator implements Comparator<Player> {
    @Override
    public int compare(Player firstPlayer, Player secondPlayer) {
        return Integer.compare(firstPlayer.getAge(), secondPlayer.getAge());
    }
}
import java.util.Set;
import java.util.*;


public abstract class CoinChanger {
    abstract public int minCoins(int amount, Set<Integer> denominations);

    private static void checkArguments(int amount, Set<Integer> denominations) {

        if (amount < 1) {
          throw new IllegalArgumentException("Amount must be at least 1");
        }
        if (denominations.size() < 1) {
          throw new IllegalArgumentException("At least one denomination is required");
        }
        for (Integer denomination: denominations) {
          if (denomination < 1) {
            throw new IllegalArgumentException("Denominations must all be positive");
          }
        }
        if (denominations.contains(1) == false){
          throw new IllegalArgumentException("Denominations must have a 1-unit coin");
        }

    }

    public static class TopDown extends CoinChanger {


        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);

            int[] memo = new int[amount];

            if (amount < 1) {
              return 0;
            }

            ArrayList<Integer> sortedList = new ArrayList<Integer>();
            for (Integer denomination: denominations){
              sortedList.add(denomination);
            }

            Collections.sort(sortedList);

            return PerformRecursion(memo, amount, sortedList);

        }


        private int PerformRecursion(int[] memo, int amount, ArrayList<Integer> sortDenominations) {

            if (amount == 0){
              return 0;
            }
            if (amount < 0){
              return -1;
            }

            if (memo[amount -1] != 0){
              return memo[amount -1];
            }

            int min = Integer.MAX_VALUE;

            for (Integer denomination: sortDenominations){
              int res = PerformRecursion(memo, amount - denomination, sortDenominations);
              if (res >= 0 && res < min){
                min = res + 1;
              }
            }

            memo[amount -1] = (min == Integer.MAX_VALUE) ? -1: min;
            return memo[amount -1];

        }


        }


    public static class BottomUp extends CoinChanger {

        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);

            ArrayList<Integer> sortedList = new ArrayList<Integer>();
            for (Integer denomination: denominations){
              sortedList.add(denomination);
            }

            Collections.sort(sortedList);

            int max = amount + 1;
            int[] set = new int[amount + 1];
            Arrays.fill(set, max);
            set[0] = 0;
            for (int i = 1; i <= amount; i++) {
              for (int j = 0; j < sortedList.size(); j++) {
                if (sortedList.get(j) <= i) {
                  set[i] = Math.min(set[i], set[i - sortedList.get(j)] + 1);
                }
              }
            }
            return set[amount] > amount ? -1 : set[amount];
        }


    }
}

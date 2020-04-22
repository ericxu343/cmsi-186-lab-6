import java.util.Set;
import java.util.*;
// TODO of course, you may wish to import more things...

public abstract class CoinChanger {
    abstract public int minCoins(int amount, Set<Integer> denominations);

    private static void checkArguments(int amount, Set<Integer> denominations) {
        // TODO: Do all of your checks here, according to the lab instructions.
        // Anything wrong? Throw an IllegalArgumentException.
        //
        // Error situations and messages are:
        //   "Amount must be at least 1"
        //   "At least one denomination is required"
        //   "Denominations must all be positive"
        //   "Denominations must have a 1-unit coin"
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

            // TODO: Do the top-down-with-memoization algorithm here. You should
            // do this recursively, so write a separate, private, recursive,
            // "helper" method. This method here will call that recursive
            // method with the memo object and the initial amount.
            int[] count = new int[amount];
            int result;

            if (amount < 1) {
              return 0;
            }

            ArrayList<Integer> sortedList = new ArrayList<Integer>();
            for (Integer denomination: denominations){
              sortedList.add(denomination);
            }
            // sort the Array list
            Collections.sort(sortedList);

            var startTime = System.currentTimeMillis();

            result = PerformRecursion(count, amount, sortedList); // do the recursion with position at 0

            var endTime = System.currentTimeMillis();
            var timeElapsed = endTime - startTime;
            System.out.println("Execution time in milliseconds: " + timeElapsed);
            return result;
            // TODO change this line, of course
        }


        private int PerformRecursion(int[] count, int amount, ArrayList<Integer> sortDenominations) {
            // Recursion Method

            if (amount == 0){
              return 0;
            }
            if (amount < 0){
              return -1;
            }

            if (count[amount -1] != 0){
              return count[amount -1];
            }

            int min = Integer.MAX_VALUE;

            for (Integer denomination: sortDenominations){
              int res = PerformRecursion(count, amount - denomination, sortDenominations);
              if (res >= 0 && res < min){
                min = res + 1;
              }
            }

            count[amount -1] = (min == Integer.MAX_VALUE) ? -1: min;
            return count[amount -1];

        }


        }


    public static class BottomUp extends CoinChanger {

        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);

            ArrayList<Integer> sortedList = new ArrayList<Integer>();
            for (Integer denomination: denominations){
              sortedList.add(denomination);
            }
            // sort the Array list
            Collections.sort(sortedList);

            var startTime = System.currentTimeMillis();

            // TODO: Implement this method using the bottom-up approach with
            // a table.
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
            var endTime = System.currentTimeMillis();
            var timeElapsed = endTime - startTime;
            System.out.println("Execution time in milliseconds: " + timeElapsed);
            return set[amount] > amount ? -1 : set[amount]; // TODO change this line, of course
        }


    }
}

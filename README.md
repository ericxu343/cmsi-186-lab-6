# CMSI 186 Lab 6 Coin Changing Starter Code

This repository contains some unfinished Java classes for you to complete.

Import this repository into a new repository of your own and write the code according to the lab [instructions](https://cs.lmu.edu/~ray/classes/plab/lab/6/). Use the provided test scripts as you complete the programs.

In your new repository, don’t forget to completely edit this README file, as _your_ repository will not contain any “unfinished Java programs.” Consider adding a fun image, but not _too_ fun, as your GitHub repository will be checked by prospective employers.

MY ACTIONS:

First, I checked the arguments to make sure they were valid within the parameters
given. I made sure the amount was greater than 1, the number of denominations was
greater than 1, the denominations were positive, and they had a 1.

Then, I worked on the TopDown method. I used memoization and I stored the amount
(count). I created an ArrayList and added the data values from the Set and then
organized it. Then I made a separate Recursion Function that helped calculate.

I also worked on the BottomUp method. Like TopDown, I made an ArrayList and added
the data values from the Set and then sorted it. Then, we calculated up to the amount
and then calculated which one was the shorted path.

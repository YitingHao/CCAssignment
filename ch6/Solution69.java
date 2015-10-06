/* 
 * Algorithms: 
 * If a number n is a perfect square, then the n locker should be open. The door will be left open, only when
 * its has odd number of factor. For example, 12 has factors 1,2,3,4,6,12. It will be toggle six times. Since
 * the original status is that all lockers are close, after even number of toggling, locker will be close.
 * However, if a number is a perfect square, for example 36, which has factors 1,2,3,4,6,9,12,18,36. Since
 * 6 * 6 = 36, 36 has odd number of factors, meaning it will be toggled odd times. Since original status is
 * close, after odd number toggling, it will be open.
 * Therefore, the lock 1,4,9,16,25,36,49,64,81,100 will be open at hte end of this process.
*/
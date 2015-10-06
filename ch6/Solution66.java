/* 
 * Method:
 * Let suppose there are total n people and among them there are number of c (c > 0) who have blue eyes.
 * 1) When c = 1, there is only one person with blue eye. When he/she looks around, he/she will realize 
 * 	  he/she is the only one who has blue eyes. Then this person will take the flight in the first evening.
 * 2) When c= 2, the person who has blue eyes will realize there are maybe two people have blue eyes or 
 *    only one person. If there is only one person who has blue eyes, he/she should have left in the first
 *    evening. Therefore, there must be two people have blue eyes. These two people leave in the second
 *    evening.
 * 3) When c = 2, the same logic applies. People who have blue eyes will realize that there should be 2 or 3
 * 	  people who have blue eyes. If there are only two people, they should have left in the second evening.
 * 	  Therefore, there are three people with blue eyes. Then they leave in the third evening.
 * 4) When c > 2, the same logic applies. Therefore, all the people who have blue eyes will leave on the
 * 	  cth night, where c is the number of people who have blue eyes.
*/
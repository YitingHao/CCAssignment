/* 
 * Method: 
 * We can use external sorting to solve this problem. Briefly, divide the large data into several blocks, 
 * order elements in each block first and then merge them. Let see an example, for sorting 90MB of data using
 * only 10MB memory. The steps should be like this:
 * 1) Load 10MB data to the memory and use quicksort to sort all the elements;
 * 2) Write the sorted array back to the disk;
 * 3) Repeat this process until that all 90MB data have been partially sorted;
 * 4) Then divide the memory into (90MB / 10MB) + 1 = 10 chunks, each chunk gets 1MB space. The first 9 
 *    input chunks are used to load data from corresponding disk. The last one is used for output. 
 *    (Here we divide memory into even amount for easy demonstration. In practice, in order to provide 
 *    better performance, the output chunk is larger than the other chunks);
 * 5) Load 1MB data of each sorted chucks into corresponding input chunks. Perform the 9-way merge and store
 * 	  the 1MB smallest data in the output chunk. When output chunk is full, write it to the final file and
 * 	  empty the output chunk. Whenever any of the 9 input chunks is empty, reload data from corresponding
 * 	  chunks in memory.
 * The overall external sorting is similar to merge sort, but need to be careful about space limitation.
*/
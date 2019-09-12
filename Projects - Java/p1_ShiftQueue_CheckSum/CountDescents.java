public class CountDescents{
 public static int countDescents(int[] xs){
  int sequences = 0;
   for(int i=0 ; i < xs.length-1; i++){
     /*only check during the first iteration if there is actually a value in xs
     if there is a value present, then there is at least 1 sequences in xs
     if xs has exactly 1 value there is only 1 sequences
     */
     if (i == 0 && xs.length == 1)
       sequences = 1;
     //if there is more than one value, compare the value at index 0 to index 1
     else if (i==0 && xs.length >1){
       sequences = 1;       
       if (xs[i+1] > xs[i])
         sequences = sequences + 1;
     }
     //check all other values
     else if (xs[i+1] > xs[i])
       sequences = sequences + 1;
  }
  return sequences;
 }
}
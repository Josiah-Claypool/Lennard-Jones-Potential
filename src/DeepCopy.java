import java.util.ArrayList;

public class DeepCopy {

    /**
     * Creates a deep copy of a 2D arraylist of doubles to avoid accidentally changing arraylist with shallow copies
     *
     * @param theArray The original arraylist that will be deep copied
     * @return A deep copy of the submitted arraylist
     */

    public static ArrayList<ArrayList<Double>> deepCopy2D(ArrayList<ArrayList<Double>> theArray) {
        ArrayList<ArrayList<Double>> fullClone = new ArrayList<>();
        int width = theArray.get(0).size();
        int length = theArray.size();
        for (int i = 0; i < length; i++) {
            ArrayList<Double> rowClone = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                rowClone.add(theArray.get(i).get(j));
            }
            fullClone.add(rowClone);
        }
        return fullClone;
    }
}

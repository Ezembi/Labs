package labs.mobile.victor.lab_1.Utils;

import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import labs.mobile.victor.lab_1.R;

/**
 * Created by Victor on 15.10.2016.
 */
public class Helper {

    public static List<Integer> getPrimeNumbers(int number) {
        LinkedList<Integer> primeNumbers = new LinkedList<>();

        for (int i = 0; i <= number; i++) {
            boolean isPrime = true;

            for (long j = 2; j < i; j++) {

                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }

    public static String listToString(List<Integer> list){
        String result = "";

        for (int i : list){
            result += i + "; ";
        }

        return result;
    }

}

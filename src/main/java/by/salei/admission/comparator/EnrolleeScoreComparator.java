package by.salei.admission.comparator;

import by.salei.admission.entity.Enrollee;

import java.util.Comparator;

public class EnrolleeScoreComparator implements Comparator<Enrollee> {

    @Override
    public int compare(Enrollee o1, Enrollee o2) {
        if (o1.getScore() > o2.getScore()) {
            return -1;
        } else if (o1.getScore() < o2.getScore()) {
            return 1;
        }
        return 0;
    }
}

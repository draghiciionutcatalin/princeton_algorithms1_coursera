/* *****************************************************************************
 *  Name:              Ionut Draghici
 *  Created:           19 March 2024 13:50
 **************************************************************************** */

import java.util.Comparator;

public class StudentCourse {
    public static final Comparator<StudentCourse> BY_NAME = new ByName();
    public static final Comparator<StudentCourse> BY_SECTION = new BySection();

    private final String name;
    private final int section;

    public StudentCourse(String name, int section) {
        this.name = name;
        this.section = section;
    }

    private static class ByName implements Comparator<StudentCourse> {
        public int compare(StudentCourse thisOne, StudentCourse other) {
            return thisOne.name.compareTo(other.name);
        }
    }

    private static class BySection implements Comparator<StudentCourse> {
        public int compare(StudentCourse thisOne, StudentCourse other) {
            return thisOne.section - other.section;
        }
    }


    public static void main(String[] args) {

    }
}

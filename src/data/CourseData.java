package src.data;

import src.models.Course;

import src.errorhandling.*;

import java.util.Scanner;

public class CourseData {

    public static Course[] setCourseInfo() {
        boolean exitCondition = true;
        Scanner input = new Scanner(System.in);

        while (exitCondition) {
            try {
                System.out.println("How many courses did you take this semester? ");
                int numberOfCourses = input.nextInt();

                if (numberOfCourses <= 0) {
                    throw new NumberOfCoursesException("You can't take a negative or zero number of courses.");
                }

                Course[] courses = new Course[numberOfCourses];

                System.out.println("Great! Now answer the below prompts so we can compile your GPA.");
                int index = 0;

                while (index < numberOfCourses) {
                    try {
                        System.out.println("What is the name of the course taken?");
                        String courseName = input.next();
                        input.nextLine();

                        System.out.println("How many course units is it? ");
                        float creditUnits = input.nextFloat();

                        System.out.println("What was your overall score out of a 100? ");
                        float coursePerformance = input.nextFloat();

                        if (creditUnits <= 0 || coursePerformance < 0 || coursePerformance > 100
                                || courseName.length() == 0 || courseName.contains("\t") || courseName.contains("\n")) {
                            throw new IncorrectDataException(
                                    "You have put in incorrect data. Please try again.");
                        }

                        courses[index] = new Course(courseName, creditUnits, coursePerformance);
                    } catch (IncorrectDataException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    index++;
                }

                exitCondition = false;
                return courses;
            } catch (NumberOfCoursesException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
        input.close();
        return null;
    }

    public static void setGrade(Course[] courses) {
        for (Course course : courses) {
            float performance = course.getPerformance();

            if (performance >= 70) {
                course.setGrade('A');
                course.setPoint(5);
            } else if (performance < 70 && performance >= 60) {
                course.setGrade('B');
                course.setPoint(4);
            } else if (performance < 60 && performance >= 50) {
                course.setGrade('C');
                course.setPoint(3);
            } else if (performance < 50 && performance >= 45) {
                course.setGrade('D');
                course.setPoint(2);
            } else if (performance < 45 && performance >= 40) {
                course.setGrade('E');
                course.setPoint(1);
            } else {
                course.setGrade('F');
                course.setPoint(0);
            }
        }
    }

    public static float setGpa(Course[] courses) {
        float totalCreditUnits = 0f;
        float totalCreditPointTimesUnit = 0f;

        for (Course course : courses) {
            totalCreditUnits += course.getCreditUnits();
        }

        for (Course course : courses) {
            totalCreditPointTimesUnit += course.getCreditUnits() * course.getPoint();
        }

        return totalCreditPointTimesUnit / totalCreditUnits;
    }

    public static void displayResults(Course[] courses, float gpa) {
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        System.out.println("| COURSE & CODE              | COURSE UNIT           | GRADE      | GRADE-UNIT          |");
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");

        for (Course course : courses) {
            System.out.printf("| %-26s | %-21d | %-10c | %-19d |\n", course.getName(), (int) course.getCreditUnits(),
                    course.getGrade(), course.getPoint());
        }

        System.out.println("|---------------------------------------------------------------------------------------|");
        System.out.printf("\nYour GPA is = %.2f to 2 decimal places.\n", gpa);
    }

}

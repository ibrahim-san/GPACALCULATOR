package src.models;

public class Course {

    private String name;
    private float creditUnits;
    private float performance;
    private char grade;
    private int point;

    public Course(String name, float creditUnits, float performance) {
        this.name = name;
        this.creditUnits = creditUnits;
        this.performance = performance;
    }

    public String getName() {
        return name;
    }

    public float getCreditUnits() {
        return creditUnits;
    }

    public float getPerformance() {
        return performance;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}

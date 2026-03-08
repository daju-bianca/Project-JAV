

import java.util.Date;

public class Applicants {
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String email;
    private final Date datetime;
    private double score;

    public Applicants(String firstName, String lastName, String middleName, String email, Date datetime, double score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.datetime = datetime;
        this.score = score;
    }


    public String getLastName() {
        return lastName;
    }

    public Date getDatetime() {
        return datetime;
    }

    public double getScore() {
        return score;
    }


    public String getEmail() {
        return email;
    }


    public void adjustScore(boolean time) {
        if (time) {
            if (this.score > 9) {
                this.score = 10;
            } else {
                this.score += 1;
            }
        } else {
            if (this.score < 1) {
                this.score = 0;
            } else {
                this.score -= 1;
            }

        }
    }


    @Override
    public String toString() {
        return "Applicants{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", datetime=" + datetime +
                ", score=" + score +
                '}';
    }
}


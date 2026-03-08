
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ApplicantsProcessor {
    private Integer uniqueApplicants;
    public List<String> topApplicants = new ArrayList<>();
    private final List<Applicants> applicantsList = new ArrayList<>();


    public ApplicantsProcessor(String filePath) throws IOException, ParseException {

        String lastName;
        String middleName;
        String firstName;
        String email;
        Date date;
        double score;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        while (true) {

            String Applicant = bufferedReader.readLine();

            if (Applicant == null) {
                break;
            }
            String[] Applicant1 = Applicant.split(",");

            String[] name = Applicant1[0].split(" ");

            if (name.length == 2) {
                firstName = name[0];
                lastName = name[1];
                middleName = " ";
            } else if (name.length == 3) {
                firstName = name[0];
                lastName = name[1];
                middleName = name[2];

            } else {
                continue;
            }
            String emailRegex = "^[a-zA-Z][a-zA-Z0-9._-]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";
            if (Applicant1[1].matches(emailRegex)) {
                email = Applicant1[1];

            } else {
                System.out.println("Email address incompatible" + Applicant1[1]);
                continue;

            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date = simpleDateFormat.parse(Applicant1[2]);
            score = Double.parseDouble(Applicant1[3]);
            Applicants applicant1 = new Applicants(firstName, lastName, middleName, email, date, score);
            applicantsList.add(applicant1);

        }
    }

    public List<Applicants> getApplicantsList() {
        return applicantsList;
    }

    public void setTopApplicants(List<String> topApplicants) {
        sortApplicants();
        for (int i = 0; i <= 2; i++) {
            topApplicants.add(applicantsList.get(i).getLastName());
        }
        this.topApplicants = topApplicants;
    }

    public double getAvgScore() {

        double sum = 0;
        int a;
        if (applicantsList.size() % 2 == 0) {
            a = applicantsList.size() / 2;
        } else {
            a = applicantsList.size() / 2 + 1;
        }
        for (int i = 0; i < a; i++) {
            sum += applicantsList.get(i).getScore();
        }

        return sum / (double) a;
    }

    public Integer getUniqueApplicants() {
        return uniqueApplicants;
    }

    public List<String> getTopApplicants() {
        return topApplicants;
    }

    public void setUniqueApplicants() {
        int uniqueApplicants = applicantsList.size();
        for (int i = 0; i <= applicantsList.size(); i++) {
            for (int j = i + 1; j < applicantsList.size(); j++) {
                if (applicantsList.get(i).getEmail().equals(applicantsList.get(j).getEmail())) {
                    uniqueApplicants -= 1;
                }
            }
        }
        this.uniqueApplicants = uniqueApplicants;
    }


    public void sortApplicants() {
        applicantsList.sort(Comparator.comparingDouble(Applicants::getScore).reversed());

    }

    public int getMindate(List<Applicants> applicantsList1) {
        int min = applicantsList1.getFirst().getDatetime().getDate();
        for (Applicants i : applicantsList1) {

            if (min > i.getDatetime().getDate()) {
                min = i.getDatetime().getDate();
            }
        }
        return min;
    }

    public void adjustScores() {
        int min = getMindate(this.applicantsList);
        for (Applicants i : applicantsList) {
            i.adjustScore(min == i.getDatetime().getDate());
        }
    }


}


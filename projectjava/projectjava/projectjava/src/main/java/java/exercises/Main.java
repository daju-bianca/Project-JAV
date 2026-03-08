
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select file");
        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("The file was not selected.");
            return;
        }

        File file = fileChooser.getSelectedFile();
        String fileName = file.getAbsolutePath();

        ApplicantsProcessor processor = new ApplicantsProcessor(fileName);
        processor.sortApplicants();
        processor.setTopApplicants(new ArrayList<>());
        processor.setUniqueApplicants();

        System.out.println("Avg score: " + processor.getAvgScore());
        System.out.println("Top 3: " + processor.getTopApplicants());
        System.out.println("Number of unique applicants: " + processor.getUniqueApplicants());
        int size = processor.getApplicantsList().size();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(processor);
        System.out.println(jsonString);
        processor.adjustScores();
        for (int i = 0; i < size; i++) {
            System.out.println(processor.getApplicantsList().get(i));
        }
    }


}

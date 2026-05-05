package services;

import models.Launch;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HistoryService {
    public void saveHistory(List<Launch> history) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("history.txt", true))) {
            for (Launch l : history) {
                bw.write(l.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Warning : could not save history, reason : " + e.getMessage());
        }
    }
}

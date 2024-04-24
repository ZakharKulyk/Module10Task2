import java.io.*;
import java.util.ArrayList;

public class TxtToJson {
    public static void main(String[] args) {
        ArrayList<User> data = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String parts[] = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    data.add(new User(name, age));
                }

            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < data.size(); i++) {
            User user = data.get(i);
            json.append("    {\n")
                    .append("        \"name\": \"").append(user.getName()).append("\",\n")
                    .append("        \"age\": ").append(user.getAge()).append("\n")
                    .append("    }");
            if (i < data.size() - 1) {
                json.append(",\n");
            } else {
                json.append("\n");
            }
        }
        json.append("]");


        try (FileWriter fileWriter = new FileWriter("user.json");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(json.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

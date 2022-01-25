package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public void save(ArrayList<Task> listOfTasks) {
        try {

            File f = new File(path);
            System.out.println(f.getAbsolutePath());

            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f, false);
            for (Task task : listOfTasks) {
                fw.write(task.parseString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> fetch() {

        File f = new File(path);
        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner scanner = new Scanner(f);

            while (scanner.hasNextLine()) {

                String input = scanner.nextLine();
                String[] data = input.split("\\|");
                boolean isDone = data[1].trim().equals("1");
                Task task;
                switch (data[0].trim()) {
                    case "T":
                        task = new ToDo(data[2].trim(), isDone);
                        listOfTasks.add(task);
                        break;
                    case "D":
                        task = new Event(data[2].trim(), data[3].trim(), isDone);
                        listOfTasks.add(task);
                        break;
                    case "E":
                        task = new Event(data[2].trim(), data[3].trim(), isDone);
                        listOfTasks.add(task);
                        break;
                }
            }

            //listOfTasks.add(new ToDo("soe"));
            
        } catch (IOException e) {
            e.printStackTrace();

        }
        return listOfTasks;


    }

}

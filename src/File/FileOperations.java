package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperations {

    public static void main(String[] args) {
        String fileName = "example.txt";
        String directoryName = "JAVA";

        // Tạo mới file
        createNewFile(fileName);

        // Lưu file
        saveFile(fileName, "Hello, world!");

        // Ghi dữ liệu vào file
        writeFile(fileName, "This is new content.");

        // Đọc file
        String content = readFile(fileName);
        System.out.println("Content of file: " + content);

        // Lấy đường dẫn file
        String filePath = getFilePath(fileName);
        System.out.println("File path: " + filePath);

        // Tạo thư mục
        createDirectory(directoryName);

        // Xóa file
        deleteFile(fileName);
    }

    // Tạo mới file
    public static void createNewFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Lưu file
    public static void saveFile(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Ghi dữ liệu vào file
    public static void writeFile(String fileName, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.newLine();
            writer.write(content);
            writer.close();
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Đọc file
    public static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
return content.toString();
    }

    // Lấy đường dẫn file
    public static String getFilePath(String fileName) {
        Path path = Paths.get(fileName);
        return path.toAbsolutePath().toString();
    }

    // Xóa file
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("File deleted: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    // Tạo thư mục
    public static void createDirectory(String directoryName) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Directory created: " + directory.getName());
            } else {
                System.out.println("Failed to create the directory.");
            }
        } else {
            System.out.println("Directory already exists.");
        }
    }
}

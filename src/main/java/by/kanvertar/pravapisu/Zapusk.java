package by.kanvertar.pravapisu;

import by.spelling.conversion.converter.BazavyKanvertar;
import by.spelling.conversion.converter.k.AKKanvertar;
import by.spelling.conversion.converter.l.ALKanvertar;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Zapusk {

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            System.out.println("Няправільная колькасьць аргумэнтаў. Іх павінна быць 4:");
            System.out.println("- правапіс у файле, які будзе канвэртавацца (a, k, l)");
            System.out.println("- шлях да txt-файла, які будзе канвэртавацца");
            System.out.println("- правапіс новага файла (a, k, l)");
            System.out.println("- шлях да  новага файла");
            System.out.println("\nПрыклад:");
            System.out.println("java -jar converter.jar a 1 l 2\n");
        } else {
            String inputStyle = args[0];
            String inputPath = args[1];
            String outputStyle = args[2];
            String outputPath = args[3];
            checkAndConvert(inputStyle, inputPath, outputStyle, outputPath);
        }
    }

    static void checkAndConvert(
            String inputStyle,
            String inputPath,
            String outputStyle,
            String outputPath) throws IOException {
        if (!validStyle(inputStyle)) {
            System.out.println("Няправільны стыль уваходнага файла.");
        } else if (!validStyle(outputStyle)) {
            System.out.println("Няправільны стыль выходнага файла.");
        } else {
            BazavyKanvertar converter = getConverterByStyles(inputStyle, outputStyle);
            convertToFile(inputPath, outputPath, converter);
        }
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    static void writeToFile(String output, String path) throws IOException {
        FileWriter myWriter = new FileWriter(path);
        myWriter.write(output);
        myWriter.close();
    }

    static void convertToFile(String inputPath, String outputPath, BazavyKanvertar converter) throws IOException {
        String input = readFile(inputPath, StandardCharsets.UTF_8);
        System.out.println(input);
        String output = converter.kanvertavać(input);
        System.out.println(output);
        writeToFile(output, outputPath);
    }

    static boolean validStyle(String style) {
        return style.equalsIgnoreCase("a")
                || style.equalsIgnoreCase("k")
                || style.equalsIgnoreCase("l");
    }

    static BazavyKanvertar getConverterByStyles(String inputStyle, String outputStyle) {
        if (inputStyle.equalsIgnoreCase("a") && outputStyle.equalsIgnoreCase("k")) {
            return new AKKanvertar();
        } else if (inputStyle.equalsIgnoreCase("a") && outputStyle.equalsIgnoreCase("l")) {
            return new ALKanvertar();
        } else {
            return new BazavyKanvertar();
        }
    }
}

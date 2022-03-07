package by.spelling.conversion;

import by.spelling.conversion.converter.BaseConverter;
import by.spelling.conversion.converter.lacink.NarkamLacinkConverter;
import by.spelling.conversion.converter.tarask.NarkamTaraskConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Launcher {

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            System.out.println("Няправільная колькасьць аргумэнтаў. Іх павінна быць 4:");
            System.out.println("- правапіс у файле, які будзе канвэртавацца (nar, tar, lac)");
            System.out.println("- шлях да txt-файла, які будзе канвэртавацца");
            System.out.println("- правапіс новага файла (nar, tar, lac)");
            System.out.println("- шлях да  новага файла");
            System.out.println("\nПрыклад:");
            System.out.println("java -jar converter.jar nar 1 lac 2\n");
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
            BaseConverter converter = getConverterByStyles(inputStyle, outputStyle);
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

    static void convertToFile(String inputPath, String outputPath, BaseConverter converter) throws IOException {
        String input = readFile(inputPath, StandardCharsets.UTF_8);
        System.out.println(input);
        String output = converter.convert(input);
        System.out.println(output);
        writeToFile(output, outputPath);
    }

    static boolean validStyle(String style) {
        return style.equalsIgnoreCase("nar")
                || style.equalsIgnoreCase("tar")
                || style.equalsIgnoreCase("lac");
    }

    static BaseConverter getConverterByStyles(String inputStyle, String outputStyle) {
        if (inputStyle.equalsIgnoreCase("nar") && outputStyle.equalsIgnoreCase("tar")) {
            return new NarkamTaraskConverter();
        } else if (inputStyle.equalsIgnoreCase("nar") && outputStyle.equalsIgnoreCase("lac")) {
            return new NarkamLacinkConverter();
        } else {
            return new BaseConverter();
        }
    }
}


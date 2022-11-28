import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Map;

public class PackRLE {

    public String newEncrypt(boolean type, String inputString) throws IOException {
        Map<Integer, Character> digitsTable = DigitsTable.digitsTable;
        StringBuilder newEncrypt = new StringBuilder();
        if (type) {
            packing(inputString, newEncrypt, digitsTable);
        } else {
            unpacking(inputString, newEncrypt, digitsTable);
        }

        return newEncrypt.toString();

    }

    /**
     * Программа посимвольно читает входяший файл, подсчитывает кол-во повторов подряд символа и записывает в новый
     * в формате двух символов: [кол-во повторов от 0-9-A-Z-a-z (где 0 - это 1 повтор, а z - 62 повтора)][сам символ].
     * <p>
     * Программа не считает кол-во повторов управляющих символов, таких как: пробел, переход на новую строку и т.д.
     * Управляющие символы находятся в диапазоне от 0 до 31, 32 - пробел.
     * Так же это сделано для улучшения коэффициента сжатия.
     *
     * @param newEncrypt  текст нового файла
     * @param digitsTable таблица для перевода чисел в буквенную форму
     */
    private void packing(@NotNull String inputString, StringBuilder newEncrypt, Map<Integer, Character> digitsTable) throws IOException {
        try (BufferedReader reader = new BufferedReader(new CharArrayReader(inputString.toCharArray()))) {
            int counterRepetitions = 0;
            int prevChar = -1;
            int newChar;

            while ((newChar = reader.read()) != -1) {
                if (prevChar > 32 || prevChar == -1) {
                    if (newChar == prevChar && counterRepetitions < 62) {
                        counterRepetitions++;
                    } else if (prevChar != -1) {
                        newEncrypt.append(digitsTable.get(counterRepetitions));
                        newEncrypt.append((char) prevChar);
                        counterRepetitions = 0;
                    }
                } else {
                    newEncrypt.append((char) prevChar);
                }
                prevChar = newChar;
            }
            if (prevChar > 32) newEncrypt.append(digitsTable.get(counterRepetitions));
            if (prevChar != -1) newEncrypt.append((char) prevChar);
        }

    }

    private void unpacking(@NotNull String inputString, StringBuilder newEncrypt, Map<Integer, Character> digitsTable) throws IOException {
        try (BufferedReader reader = new BufferedReader(new CharArrayReader(inputString.toCharArray()))) {
            boolean counter = false;
            int counterRepetitions = 0;
            int newChar;
            while ((newChar = reader.read()) != -1) {
                if (newChar > 32) {
                    if (counter) {
                        while (counterRepetitions != 0) {
                            newEncrypt.append((char) newChar);
                            counterRepetitions--;
                        }
                        counter = false;
                    } else {
                        counterRepetitions = DigitsTable.getValue(digitsTable, (char) newChar) + 1;
                        counter = true;
                    }
                } else {
                    newEncrypt.append((char) newChar);
                    counter = false;
                }

            }
        }
    }

}
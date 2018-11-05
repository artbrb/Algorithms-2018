package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String firs, String second) {
        int fisLength = firs.length();
        int secondLength = second.length();
        char[][] wordsMatrix = new char[fisLength + 1][secondLength + 1];
        StringBuilder maxString = new StringBuilder();

        for (int i = 1; i < fisLength + 1; i++) {
            wordsMatrix[i][0] = firs.charAt(i - 1);
        }
        for (int j = 1; j < secondLength + 1; j++) {
            wordsMatrix[0][j] = second.charAt(j - 1);
        }

        for (int i = 1; i < fisLength + 1; i++) {
            for (int j = 1; j < secondLength + 1; j++) {
                StringBuilder currentString = new StringBuilder();
                int x = i;
                int y = j;
                if (wordsMatrix[i][0] == wordsMatrix[0][j]) {
                    while (x + 1 <= fisLength + 1 && y + 1 <= secondLength + 1 && wordsMatrix[x][0] == wordsMatrix[0][y]) {
                        currentString.append(wordsMatrix[x][0]);
                        x++;
                        y++;
                    }
                }
                if (currentString.length() > maxString.length()) maxString = currentString;
            }
        }
        return maxString.toString();
    }

    //Трудоёмкость:    O(n * m)
    //Ресурсоёмкость:  O(n * m)
    //m, n - длины входных слов

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        if (limit <= 0) {
            return 0;
        } else {
            int primesNumber = limit - 1;
            for (int k = 2; k <= limit; k++) {
                for (int i = 2; i <= Math.sqrt(k); i++) {
                    if (k % i == 0) {
                        primesNumber--;
                        break;
                    }
                }
            }
            return primesNumber;
        }
    }

    //Трудоёмкость:    O(n * sqrt(n))
    //Ресурсоёмкость:  O(1)
    //n - limit

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws IOException {
        String s;
        Integer rows = -1;
        Integer count = 0;
        Integer columns = 0;
        List<String> list = new ArrayList<>();
        Set<String> finalResult = new HashSet<>();
        Set<Pair<Integer, Integer>> passedCoordinates = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputName));

        while ((s = reader.readLine()) != null) {
            String[] splitArray = s.split(" ");
            columns = splitArray.length - 1;
            rows++;
            list.addAll(Arrays.asList(splitArray));
        }
        String[][] matrix = new String[rows + 1][columns + 1];
        for (int row = 0; row <= rows; row++) {
            for (int column = 0; column <= columns; column++){
                matrix[row][column] = list.get(row * columns + column);
            }
        }
        Integer tt = matrix.length;

        for (String word: words) {
            for (int row = 0; row <= rows; row++) {
                for (int column = 0; column <= columns; column++) {
                    if (String.valueOf(word.charAt(0)).equals(matrix[row][column])) {
                        count = 0;
                        wordsSearch(finalResult, passedCoordinates, matrix, word, row, column, count, rows, columns);
                    }
                }
            }
        }
        return finalResult;

    }

    private static void wordsSearch(Set<String> finalResult, Set<Pair<Integer, Integer>> passedCoordinates,
                                    String[][] matrix, String word,int row,int column,int count,int rows,int columns){

        Pair<Integer, Integer> rightPair = new Pair<>(row, column + 1);
        Pair<Integer, Integer> leftPair = new Pair<>(row, column - 1);
        Pair<Integer, Integer> upPair = new Pair<>(row - 1, column);
        Pair<Integer, Integer> downPair = new Pair<>(row + 1, column);

        if (word.length() - 1 != count) {

            if (checkOutOfIndex(row, column + 1, rows, columns) &&
                    matrix[row][column + 1].equals(String.valueOf(word.charAt(count + 1))) &&
                    !passedCoordinates.contains(rightPair)) {
                count++;
                passedCoordinates.add(rightPair);
                wordsSearch(finalResult, passedCoordinates, matrix, word, row, column, count, rows, columns);
            }

            if (checkOutOfIndex(row, column - 1, rows, columns) &&
                    matrix[row][column - 1].equals(String.valueOf(word.charAt(count + 1))) &&
                    !passedCoordinates.contains(leftPair)) {
                count++;
                passedCoordinates.add(leftPair);
                wordsSearch(finalResult, passedCoordinates, matrix, word, row, column, count, rows, columns);
            }

            if (checkOutOfIndex(row - 1, column, rows, columns) &&
                    matrix[row - 1][column].equals(String.valueOf(word.charAt(count + 1))) &&
                    !passedCoordinates.contains(upPair)) {
                count++;
                passedCoordinates.add(upPair);
                wordsSearch(finalResult, passedCoordinates, matrix, word, row, column, count, rows, columns);
            }

            if (checkOutOfIndex(row + 1, column, rows, columns) &&
                    matrix[row + 1][column].equals(String.valueOf(word.charAt(count + 1))) &&
                    !passedCoordinates.contains(downPair)) {
                count++;
                passedCoordinates.add(downPair);
                wordsSearch(finalResult, passedCoordinates, matrix, word, row, column, count, rows, columns);
            }
        } else {
            finalResult.add(word);
        }
    }

    private static boolean checkOutOfIndex(int row, int column, int rows, int columns) {
        return row < rows && row >= 0 && column < columns && column >= 0;
    }
}

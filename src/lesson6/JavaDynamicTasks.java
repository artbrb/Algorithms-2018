package lesson6;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        StringBuilder longestCommonSub = new StringBuilder();
        Integer firstLength = first.length();
        Integer secondLength = second.length();
        int[][] matrix = new int[secondLength + 1][firstLength + 1];

        for (int j = 0; j < firstLength + 1; j++) {
            matrix[0][j] = 0;
        }
        for (int i = 0; i < secondLength + 1; i++) {
            matrix[i][0] = 0;
        }

        for (int i = 1; i < secondLength; i++) {
            for (int j = 1; j < firstLength; j++) {

                if (first.charAt(j - 1) == second.charAt(i - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }

            }
        }
            int i = secondLength;
            int j = firstLength;
            while (i > 0 && j > 0) {

                if (first.charAt(j - 1) == second.charAt(i - 1)) {
                    longestCommonSub.append(first.charAt(j - 1));
                    i--;
                    j--;
                } else if (matrix[i - 1][j] > matrix[i][j - 1]) i--;
                else j--;

            }
            return longestCommonSub.reverse().toString();
        }

    //Трудоёмкость:    O(n * m)
    //Ресурсоёмкость:  O(n * m)
    //     m, n - длины входных слов

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        throw new NotImplementedError();
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */

    //Трудоёмкость:    O(n * m)
    //Ресурсоёмкость:  O(n * m)
    //     m, n - количество строк и столбцов во входной матрице

    public static int shortestPathOnField(String inputName) throws IOException {
        int columns;
        int rows = 0;
        List<String[]> inputRows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String string;

        while((string = reader.readLine())!= null) {
                String[] arrayOfNumbers = string.split(" ");
                rows++;
                inputRows.add(arrayOfNumbers);
        }
        reader.close();
        columns = inputRows.get(0).length;

        String[][] stringMatrix = new String[rows][columns];
        int[][] numberMatrix = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            stringMatrix[row] = inputRows.get(row);
        }

        numberMatrix[0][0] = Integer.parseInt(stringMatrix[0][0]);

        for (int column = 1; column < columns; column++) {
            numberMatrix[0][column] = numberMatrix[0][column - 1] + Integer.parseInt(stringMatrix[0][column]);
        }
        for (int row = 1; row < rows; row++) {
            numberMatrix[row][0] = numberMatrix[row - 1][0] + Integer.parseInt(stringMatrix[row][0]);
        }

        for (int row = 1; row < rows; row++) {
            for (int column = 1; column < columns; column++) {

                Integer diagonal = numberMatrix[row - 1][column - 1];
                Integer toTheRight = numberMatrix[row][column - 1];
                Integer wayDown = numberMatrix[row - 1][column];

                numberMatrix[row][column] = Integer.parseInt(stringMatrix[row][column])
                        + Math.min(toTheRight, Math.min(diagonal, wayDown));
            }
        }
        return numberMatrix[rows - 1][columns - 1];
    }



    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}

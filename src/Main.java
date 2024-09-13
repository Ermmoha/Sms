import java.util.*;

public class Main {

    public static final int PARA_MAX = 16;
    public static final int PARA_MIN = 1;
    public static final int COUNT_NOTIFY = 50;

    public static void main(String[] args) {
        // Ввод
        Scanner scanner = new Scanner(System.in);

        // Считываем количество SMS-сообщений
        System.out.println("Введите количество SMS-сообщений:");
        int numberOfVotes = scanner.nextInt();

        // Проверка на допустимое количество сообщений
        if (numberOfVotes < 0 || numberOfVotes > COUNT_NOTIFY) {
            System.out.println("Ошибка: количество сообщений вне допустимого диапазона.");
            scanner.close();
            return;
        }

        // Создаем HashMap для хранения количества голосов за каждую пару
        HashMap<Integer, Integer> voteCounts = new HashMap<>();

        // Инициализируем HashMap, чтобы каждая пара была представлена с начальным количеством голосов равным 0
        for (int i = PARA_MIN; i <= PARA_MAX; i++) {
            voteCounts.put(i, 0);
        }

        // Считываем номера пар из входных данных и обновляем количество голосов
        System.out.println("Введите номера пар:");
        for (int i = 0; i < numberOfVotes; i++) {
            int pairNumber = scanner.nextInt();

            // Проверяем, что номер пары находится в допустимом диапазоне
            if (pairNumber >= PARA_MIN && pairNumber <= PARA_MAX) {
                // Увеличиваем количество голосов для выбранной пары
                voteCounts.put(pairNumber, voteCounts.get(pairNumber) + 1);
            } else {
                System.out.println("Ошибка: номер пары должен быть от 1 до 16.");
            }
        }

        // Преобразуем HashMap в список записей и сортируем его по количеству голосов (в порядке убывания)
        List<Map.Entry<Integer, Integer>> sortedVotes = new ArrayList<>(voteCounts.entrySet());
        sortedVotes.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Выводим отсортированные пары и их количество голосов
        System.out.println("Результаты голосования:");
        for (Map.Entry<Integer, Integer> entry : sortedVotes) {
            if (entry.getValue() > 0) { // Печатаем только пары, которые получили голоса
                System.out.println("Пара " + entry.getKey() + ": " + entry.getValue() + " голосов");
            }
        }

        // Закрываем Scanner для освобождения ресурсов
        scanner.close();
    }
}

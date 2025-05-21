import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class DataManager {
    private static final String DATA_FILE = "data.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void saveData(AppData data) {
        try {
            mapper.writeValue(new File(DATA_FILE), data);
            System.out.println("Данные успешно сохранены в " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    public static AppData loadData() {
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                return mapper.readValue(file, AppData.class);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        }
        return new AppData(); // Возвращаем новые данные, если файла нет
    }
}
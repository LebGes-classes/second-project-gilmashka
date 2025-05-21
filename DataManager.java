import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    private static final String DATA_FILE = "data.bin";

    public static void saveData(AppData data) {
        try (Output output = new Output(new FileOutputStream(DATA_FILE))) {
            Kryo kryo = new Kryo();


            kryo.register(AppData.class);
            kryo.register(ArrayList.class);
            kryo.register(Warehouse.class);
            kryo.register(SalesPoint.class);
            kryo.register(Employee.class);
            kryo.register(Product.class);
            kryo.register(Customer.class);
            kryo.register(WarehouseCell.class);
            kryo.register(HashMap.class);

            kryo.writeObject(output, data);
            System.out.println("Данные сохранены в " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    public static AppData loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new AppData();
        }

        try (Input input = new Input(new FileInputStream(DATA_FILE))) {
            Kryo kryo = new Kryo();


            kryo.register(AppData.class);
            kryo.register(ArrayList.class);
            kryo.register(Warehouse.class);
            kryo.register(SalesPoint.class);
            kryo.register(Employee.class);
            kryo.register(Product.class);
            kryo.register(Customer.class);
            kryo.register(WarehouseCell.class);
            kryo.register(HashMap.class);

            return kryo.readObject(input, AppData.class);
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке: " + e.getMessage());
            return new AppData();
        }
    }
}
package bog;

/**
 * Created by zac on 16.04.17.
 */

public class DataModel {
    private String data;
    private String vector;

    public DataModel(String data, String vector) {
        this.data = data;
        this.vector = vector;
    }

    public String getData() {
        return data;
    }

    public String getVector() {
        return vector;
    }
}

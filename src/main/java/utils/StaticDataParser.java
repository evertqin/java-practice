package utils;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruoguqin on 2/15/16.
 */
public class StaticDataParser {

    List<Pair<Integer, Integer>> _data;

    public StaticDataParser() {

    }

    public void parseCsv(String filePath) {
        if(_data == null) {
            _data = new ArrayList<>();
            
        }
    }

}

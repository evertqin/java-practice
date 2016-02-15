package com.tripplan.info.userinfo;

import com.sun.deploy.util.ArrayUtil;
import com.sun.deploy.util.StringUtils;
import com.tripplan.info.utils.StaticDataParser;

import java.util.*;

/**
 * Created by ruogu on 2/15/16.
 */
public final class Processor {


    private List<List<Integer>> _data;


    private Map<Integer, List<Integer>> _graph;
    private final String STATIC_FILE = "/home/ruogu/projects/java-practice/src/main/resources/staticData.csv";


    public List<List<Integer>> get_data() {
        return _data;
    }

    public Map<Integer, List<Integer>> get_graph() {
        return _graph;
    }

    public Processor() {

    }


    public Processor initProcessor() {
        return initProcessor(STATIC_FILE);
    }

    public Processor initProcessor(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            filePath = STATIC_FILE;
        }

        this._data = StaticDataParser.parseCsv(STATIC_FILE);

        return this;
    }

    public Processor bidirectionalEdges() {
        List<List<Integer>> bidirectionalData = new ArrayList<>();
        for (List<Integer> entry : this._data) {
            bidirectionalData.add(entry);
            List<Integer> newEntry = new ArrayList<>();
            newEntry.add(entry.get(1));
            newEntry.add(entry.get(0));
            bidirectionalData.add(newEntry);
        }
        _data = bidirectionalData;

        return this;
    }

    public Processor groupBy() {
        if (_graph == null) {
            _graph = new HashMap<>();
        }

        for (List<Integer> entry : this._data) {
            if (!_graph.containsKey(entry.get(0))) {
                _graph.put(entry.get(0), new ArrayList<>());
            }

            List<Integer> mapEntry = _graph.get(entry.get(0));
            mapEntry.add(entry.get(1));
            _graph.put(entry.get(0), mapEntry);
        }

        return this;
    }


    private boolean aggregateOne(Integer key, List<Integer> edges) {

        Set<Integer> nodes = new HashSet<>();
        nodes.add(key);
        for (Integer edge : edges) {
            nodes.add(edge);
        }

        Integer target = Collections.min(edges);
        target = target < key ? target : key;
        boolean isNewEdges = false;

        //isNewEdges = key != target && nodes.size() > 2;

        List<Integer> targetList = _graph.containsKey(target) ? _graph.get(target) : new ArrayList<>();
        for (Integer n : nodes) {
            if (n != target) {
                if(targetList.contains(n)) {
                    continue;
                }
                isNewEdges = true;
                targetList.add(n);
            }
        }

        _graph.put(target, targetList);

        System.out.println(_graph);
        return isNewEdges;
    }

    public Processor aggregate() {
        boolean isNewEdges = true;

        while (isNewEdges) {
            isNewEdges = false;
            Iterator it = _graph.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();

                Integer key = (Integer) pair.getKey();
                List<Integer> edges = (List<Integer>) pair.getValue();

                isNewEdges |= aggregateOne(key, edges);
            }
        }

        return this;
    }


}

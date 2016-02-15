package com.tripplan.info;

import com.tripplan.info.userinfo.Processor;
import com.tripplan.info.utils.StaticDataParser;

/**
 * Created by ruogu on 2/15/16.
 */
public class Program {
    public static void main(String[] args) {
        System.out.println("Program Started");
        Processor processor = new Processor();
        processor.initProcessor().bidirectionalEdges().groupBy().aggregate();
        System.out.println(processor.get_graph());
    }
}

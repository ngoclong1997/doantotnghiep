package com.drato.graduationthesis.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartUtils {

    public static List<String> generatePointLabels() {
        List<String> values = new ArrayList<>();
        float i = 0;
        while (i <= 10) {
            values.add(String.valueOf(i));
            i += 0.25;
        }
        return values;
    }

    public static String getBarChartData(List<String> lstLabels, List<Integer> lstData, String backgroundColor) {
        JsonObject result = new JsonObject();
        result.add("labels", createStringJsonArray(lstLabels));
        JsonArray dataSets = new JsonArray();
        JsonObject dataSetsObject = new JsonObject();
        dataSetsObject.add("data", createIntegerJsonArray(lstData));
        dataSetsObject.addProperty("backgroundColor", backgroundColor == null ? "#2196f3" : backgroundColor);
        dataSets.add(dataSetsObject);
        result.add("datasets", dataSets);
        return result.toString();
    }

    public static String getPieChartData(List<Float> data, List<String> labels, List<String> colors) {
        if (colors == null) colors = getColors(data.size());
        JsonObject result = new JsonObject();

        JsonArray dataSets = new JsonArray();
        JsonObject dataSetsObject = new JsonObject();
        dataSetsObject.add("data", createFloatJsonArray(data));
        dataSetsObject.add("backgroundColor", createStringJsonArray(colors));
        dataSets.add(dataSetsObject);
        result.add("datasets", dataSets);
        result.add("labels", createStringJsonArray(labels));
        return result.toString();
    }

    public static JsonArray createIntegerJsonArray(List<Integer> lst) {
        JsonArray jsonArray = new JsonArray();
        for (Integer value : lst) {
            jsonArray.add(value);
        }
        return jsonArray;
    }

    public static JsonArray createStringJsonArray(List<String> lst) {
        JsonArray jsonArray = new JsonArray();
        for (String value : lst) {
            jsonArray.add(value);
        }
        return jsonArray;
    }

    public static JsonArray createFloatJsonArray(List<Float> lst) {
        JsonArray jsonArray = new JsonArray();
        for (Float value : lst) {
            jsonArray.add(value);
        }
        return jsonArray;
    }

    public static JsonArray createColorJsonArray(String color, int numberOfElements) {
        JsonArray colors = new JsonArray();
        for (int i = 0; i < numberOfElements; i++) {
            colors.add(color);
        }
        return colors;
    }

    public static List<String> getColors(int numberOfColor) {
        List<String> colors = Arrays.asList("#ed5565", "#1ab394" , "#23c6c8", "#f8ac59", "#1c84c6");
        return colors.subList(0, numberOfColor);
    }
}

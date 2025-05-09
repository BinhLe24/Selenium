package test_data;

import com.google.gson.Gson;

import test_data.computer.ComputerData;

public class TestGSON {
    // Convert from JSON to Java Object
    // Convert Java Object to JSON

    public static void main(String[] args) {
        String JSONString = "{\r\n" + //
                "        \"processorType\": \"Fast\",\r\n" + //
                "        \"ram\": \"8 GB\",\r\n" + //
                "        \"hdd\": \"320 GB\",\r\n" + //
                "        \"software\": \"Image Viewer\"\r\n" + //
                "    }";
        Gson gson = new Gson();
        ComputerData computerData = gson.fromJson(JSONString, ComputerData.class);
        System.out.println(computerData);

        System.out.println(gson.toJson(computerData));
    }
}

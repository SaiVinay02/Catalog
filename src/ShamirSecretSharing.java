import org.json.JSONObject;
import org.json.JSONArray;
import java.math.BigInteger;

public class ShamirSecretSharing {

    public static void main(String[] args) {
        // Sample test case provided in JSON format
        String jsonInput = "{\n" +
                "\"keys\": {\n" +
                "\"n\": 10,\n" +
                "\"k\": 7\n" +
                "},\n" +
                "\"1\": {\n" +
                "\"base\": \"6\",\n" +
                "\"value\": \"13444211440455345511\"\n" +
                "},\n" +
                "\"2\": {\n" +
                "\"base\": \"15\",\n" +
                "\"value\": \"aed7015a346d63\"\n" +
                "},\n" +
                "\"3\": {\n" +
                "\"base\": \"15\",\n" +
                "\"value\": \"6aeeb69631c227c\"\n" +
                "},\n" +
                "\"4\": {\n" +
                "\"base\": \"16\",\n" +
                "\"value\": \"e1b5e05623d881f\"\n" +
                "},\n" +
                "\"5\": {\n" +
                "\"base\": \"8\",\n" +
                "\"value\": \"316034514573652620673\"\n" +
                "},\n" +
                "\"6\": {\n" +
                "\"base\": \"3\",\n" +
                "\"value\": \"2122212201122002221120200210011020220200\"\n" +
                "},\n" +
                "\"7\": {\n" +
                "\"base\": \"3\",\n" +
                "\"value\": \"20120221122211000100210021102001201112121\"\n" +
                "},\n" +
                "\"8\": {\n" +
                "\"base\": \"6\",\n" +
                "\"value\": \"20220554335330240002224253\"\n" +
                "},\n" +
                "\"9\": {\n" +
                "\"base\": \"12\",\n" +
                "\"value\": \"45153788322a1255483\"\n" +
                "},\n" +
                "\"10\": {\n" +
                "\"base\": \"7\",\n" +
                "\"value\": \"1101613130313526312514143\"\n" +
                "}\n" +
                "}";

        // Parse the input JSON
        JSONObject jsonObject = new JSONObject(jsonInput);
        JSONObject keys = jsonObject.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");

        // Decoding values from base to decimal
        int[] xValues = new int[k];
        long[] yValues = new long[k];

        // Read the first k points from the JSON and decode the y-values
        for (int i = 1; i <= k; i++) {
            String base = jsonObject.getJSONObject(String.valueOf(i)).getString("base");
            String value = jsonObject.getJSONObject(String.valueOf(i)).getString("value");

            xValues[i-1] = i; // x values are just 1, 2, 3, ..., k
            yValues[i-1] = decodeBase(base, value);
        }

        // Calculate the secret (constant term c) using Lagrange interpolation
        long secret = calculateConstantTerm(xValues, yValues, k);
        System.out.println("Secret constant term c: " + secret);
    }

    // Function to decode a value from a given base
    public static long decodeBase(String baseStr, String valueStr) {
        int base = Integer.parseInt(baseStr);
        return new BigInteger(valueStr, base).longValue();
    }

    // Function to calculate the constant term c using Lagrange interpolation at x = 0
    public static long calculateConstantTerm(int[] xValues, long[] yValues, int k) {
        long c = 0;
        for (int i = 0; i < k; i++) {
            long L_i_0 = 1;
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    L_i_0 *= (0 - xValues[j]) / (xValues[i] - xValues[j]);
                }
            }
            c += yValues[i] * L_i_0;
        }
        return c;
    }
}

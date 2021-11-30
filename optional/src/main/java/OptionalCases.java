import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.alibaba.fastjson.JSON;

/**
 * @author chopin
 * @version 1.0
 * @description: TODO
 * @date 2021/11/30 13:48
 */
public class OptionalCases {
    public static void main(String[] args) {
        ifpresentCaseOne();
    }

    public static void ifpresentCaseOne() {
        String jsonData = "{\n" +
            "    \"name\":\"Ben\",\n" +
            "    \"active\":\"TRUE\",\n" +
            "    \"id\":10,\n" +
            "    \"sysProperty\": {\n" +
            "        \"dataTimestamp\":\"1589980440812\",\n" +
            "        \"datastream\":\"3300_0_5750\",\n" +
            "        \"satisfaction\":\"TRUE\"\n" +
            "    }\n" +
            "}";
        Map<String, Object> map = new HashMap<>(16);

        Optional.ofNullable(JSON.parseObject(jsonData).getBoolean("active000")).ifPresent(var -> map.put("active", var));
        // Output: {}
        System.out.println(map);

        Optional.ofNullable(JSON.parseObject(jsonData).getBoolean("active")).ifPresent(var -> map.put("active", var));
        // Output: {active=true}
        System.out.println(map);

    }
}
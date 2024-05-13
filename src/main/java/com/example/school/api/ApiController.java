package com.example.school.api;

import com.example.school.bin.Bin;
import com.example.school.bin.BinRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
public class ApiController {

    @Value("${API_URL}")
    private String url;
    @Value("${API_PAGE}")
    private String page;
    @Value("${API_KEY}")
    private String key;
    private final BinRepository binRepository;

    public ApiController(BinRepository binRepository) {
        this.binRepository = binRepository;
    }


//    @GetMapping("/api")
    public String callAPI() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = url + page + key;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine).append("\n");
            }
            urlConnection.disconnect();

            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());

                long perPage = (long) jsonObject.get("perPage");
                System.out.println("perPage: " + perPage);

                JSONArray dataArray = (JSONArray) jsonObject.get("data"); // 데이터

                long idx = 0;
                for (Object obj : dataArray) {
                    JSONObject dataObject = (JSONObject) obj;

                    Bin bin = new Bin();

                    String dateTime = (String) dataObject.get("데이터기준일자"); // 문자열 데이터 기준 가져오기
                    LocalDateTime dateTimeParse = LocalDateTime.parse(dateTime + "T00:00:00"); // 날짜 형식으로 파싱

                    // bin에 저장
                    bin.setId(idx++);
                    bin.setData_data(dateTimeParse);
                    bin.setLocation_name((String) dataObject.get("설치위치"));
                    bin.setMetropolitan_city((String) dataObject.get("시도명"));
                    bin.setCity((String) dataObject.get("시군구명"));
                    bin.setHardness(Double.parseDouble((String) dataObject.get("경도")));
                    bin.setLatitude(Double.parseDouble((String) dataObject.get("위도")));

                    binRepository.save(bin);
                }


            } catch (ParseException e) {
                System.err.println("Parsing error:" + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}

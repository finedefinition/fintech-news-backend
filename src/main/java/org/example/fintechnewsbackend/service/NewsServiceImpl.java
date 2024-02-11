package org.example.fintechnewsbackend.service;


import org.example.fintechnewsbackend.dto.NewsApiResponse;
import org.example.fintechnewsbackend.model.News;
import org.example.fintechnewsbackend.model.Ticker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NewsServiceImpl implements NewsService {
    @Value("${polygon.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.polygon.io/v2/reference/news?limit=100&ticker=%s&apiKey=%s&published_utc.gt=%s&published_utc.lt=%s";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<News> getAllNews(Ticker ticker, LocalDateTime firstDate, LocalDateTime lastDate) {
        String startDate = firstDate.format(DateTimeFormatter.ISO_DATE_TIME);
        String endDate = lastDate.format(DateTimeFormatter.ISO_DATE_TIME);
        String selectedTicker = ticker.name();
        String companyName = ticker.getFullName(); // Get the full name of the ticker
        String url = String.format(API_URL, selectedTicker, apiKey, startDate, endDate);
        System.out.println(url);
        ResponseEntity<NewsApiResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, NewsApiResponse.class);
        NewsApiResponse newsApiResponse = response.getBody();

        if (newsApiResponse != null && newsApiResponse.getResults() != null) {
            List<News> newsItems = newsApiResponse.getResults().stream()
                    .filter(news -> news.getTitle().contains(companyName)) // Filter by ticker's full name
                    .collect(Collectors.toList());
            return newsItems;
        }

        return new ArrayList<>();
    }
}

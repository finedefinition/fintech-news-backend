package org.example.fintechnewsbackend.controller;

import org.example.fintechnewsbackend.model.News;
import org.example.fintechnewsbackend.model.Ticker;
import org.example.fintechnewsbackend.service.NewsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class NewsController {

    private final NewsService newsService;

    // Constructor-based injection of the NewsService
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews(
            @RequestParam Ticker ticker,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime firstDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastDate) {

        List<News> newsList = newsService.getAllNews(ticker, firstDate, lastDate);
        if (newsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(newsList);
    }
}
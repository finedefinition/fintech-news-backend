package org.example.fintechnewsbackend.service;

import org.example.fintechnewsbackend.model.News;
import org.example.fintechnewsbackend.model.Ticker;
import java.time.LocalDateTime;
import java.util.List;

public interface NewsService {
    List<News> getAllNews(Ticker ticker, LocalDateTime firstDate, LocalDateTime lastDate);
}

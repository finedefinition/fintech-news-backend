package org.example.fintechnewsbackend.dto;

import lombok.Data;
import org.example.fintechnewsbackend.model.News;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NewsApiResponse {

    private List<News> results;

    private String status;

    private String requestId;

    private int count;

    private String nextUrl;

}

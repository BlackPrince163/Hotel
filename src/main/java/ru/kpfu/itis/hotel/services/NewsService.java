package ru.kpfu.itis.hotel.services;

import ru.kpfu.itis.hotel.models.News;

import java.util.List;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

public interface NewsService {
    void save(News entity);

    void delete(News entity);

    List<News> findByTag(String tagName);

    List<News> getAllNews();
}

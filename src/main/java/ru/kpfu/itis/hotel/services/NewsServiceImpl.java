package ru.kpfu.itis.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.hotel.models.News;
import ru.kpfu.itis.hotel.repositories.NewsRepository;

import java.util.List;

/**
 * 06.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void save(News entity) {
        newsRepository.save(entity);
    }

    @Override
    public void delete(News entity) {
        newsRepository.delete(entity);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<News> findByTag(String tagName) {
        return newsRepository.findByTag(tagName);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

}

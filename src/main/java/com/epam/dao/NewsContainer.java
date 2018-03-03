package com.epam.dao;

import com.epam.model.Book;
import com.epam.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsContainer {
    private List<News> newsList;

    public NewsContainer() {
        newsList=new ArrayList<>();
        init();
    }

    private void init(){
        newsList.add(new News(1,"News1","Interesting news 1","Some text of news","http://ur1 1"));
        newsList.add(new News(2,"News2","Interesting news 2","Some text of news","http://url 2"));
        newsList.add(new News(3,"News3","Interesting news 3","Some text of news","http://url 3"));
    }
    public void add(News news){
        newsList.add(news);
    }

    public boolean contains(News news){
        return newsList.contains(news);
    }

    public boolean containsById(News news){
        for (News b : newsList) {
            if(b.getId().equals(news.getId())){
                return true;
            }
        }
        return false;
    }
}

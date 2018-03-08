package com.epam.dao;

import com.epam.model.SingleNews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsPaperDao {
    private List<SingleNews> newsList;

    public NewsPaperDao() {
        newsList = new ArrayList<>();
        init();
    }

    private void init() {
        newsList.add(new SingleNews(1, "Gold-medal", "Sport", "Gold medalist Abramenko carries Ukrainian flag at Winter Olympics 2018 closing ceremony", "https://24tv.ua"));
        newsList.add(new SingleNews(2, "Big-Bang", "Science", "At the time of the Big Bang, all the matter in the universe was smooshed into an incredibly hot, infinitely dense speck of matter.", "https://www.ukr.net"));
        newsList.add(new SingleNews(3, "Luxury-labels", "Society", "The luxury labels coming out of Africa", "http://www.bbc.com"));
        newsList.add(new SingleNews(4, "Box", "Sport", "Ukrainian Artem Dalakian becomes new WBA flyweight champion", "https://zik.ua"));
        newsList.add(new SingleNews(5, "Dark-Wave", "Science", "At the edge of life and death is a spreading dark wave", "https://newsone.ua"));
        newsList.add(new SingleNews(6, "Heart-attack", "Health", "Waist size bigger heart attack risk in women, report says", "https://www.telegraph.co.uk"));
        newsList.add(new SingleNews(7, "Olympics", "Sport", "Winter Olympics 2018 Schedule of Ukrainian team", "https://news.sky.com"));
        newsList.add(new SingleNews(8, "Religious-Mummies", "Science", "At least 40 limestone sarcophagi that held mummified burials were discovered in the cemetery.", "https://www.express.co.uk"));
        newsList.add(new SingleNews(9, "Penguin-colony", "Nature", "Penguin super-colony spotted from space", "http://www.bbc.com/news"));
        newsList.add(new SingleNews(10, "Shakhtar", "Sport", "Shakhtar Donetsk beats Roma", "https://www.theverge.com"));

    }

    public void add(SingleNews news) {
        newsList.add(news);
    }

    public List<SingleNews> getAll() {
        return newsList;
    }

    public SingleNews getById(Integer id) {
        for (SingleNews singleNews : newsList) {
            if (singleNews.getId().equals(id)) {
                return singleNews;
            }
        }
        return null;
    }

    public List<SingleNews> getByCategory(String category) {
        List<SingleNews> list = new ArrayList<>();
        for (SingleNews singleNews : newsList) {
            if (singleNews.getCategory().equals(category)) {
                list.add(singleNews);
            }
        }
        return list;
    }

    public List<SingleNews> getByTitle(String title) {
        List<SingleNews> list = new ArrayList<>();
        for (SingleNews singleNews : newsList) {
            if (singleNews.getTitle().equals(title)) {
                list.add(singleNews);
            }
        }
        return list;
    }

    public List<SingleNews> getByTitleAndCategory(String title, String category) {
        List<SingleNews> list = new ArrayList<>();
        for (SingleNews singleNews : newsList) {
            if (singleNews.getTitle().equals(title) && singleNews.getCategory().equals(category)) {
                list.add(singleNews);
            }
        }
        return list;
    }
    //-------------------------

    public boolean contains(SingleNews singleNews) {
        return newsList.contains(singleNews);
    }

    public boolean containsById(SingleNews singleNews) {
        for (SingleNews currentNews : newsList) {
            if (currentNews.getId().equals(singleNews.getId())) {
                return true;
            }
        }
        return false;
    }

    public void delete(SingleNews singleNews) {
        newsList.remove(singleNews);
    }

    public boolean delete(Integer id) { //переписати не можна видаляти в ытераторы
        Iterator<SingleNews> iter = newsList.iterator();
        while (iter.hasNext()) {
            SingleNews singleNews = iter.next();
            if (singleNews.getId().equals(id)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    public void update(SingleNews oldSingleNews, SingleNews newSingleNews) {
        newsList.remove(oldSingleNews);
        newsList.add(newSingleNews);

/*
        Iterator<SingleNews> iter = newsList.iterator();
        boolean oldNewsDeleted = false;
        while (iter.hasNext()) {
            if(iter.next().getId().equals(oldSingleNews.getId())){
                iter.remove();
                oldNewsDeleted = true;
            }
        }
        if(oldNewsDeleted){
            newsList.add(newSingleNews);
        }
*/
    }

    public List<SingleNews> getAllByAuthor(String category) {//перейменувати на катерогію
        List<SingleNews> list = new ArrayList<>();
        for (SingleNews singleNews : newsList) {
            if (singleNews.getCategory().equals(category)) {
                list.add(singleNews);
            }
        }
        return list;
    }


}

package com.epam.dao;

import com.epam.model.SingleNews;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsPaperDao {
    private List<SingleNews> newsList;

    public NewsPaperDao() {
        newsList=new ArrayList<>();
        init();
    }

    private void init(){
        newsList.add(new SingleNews(1,"News1","Sport","Some text of news","http://ur1 1"));
        newsList.add(new SingleNews(2,"News2","Science","Some text of news","http://url 2"));
        newsList.add(new SingleNews(3,"News3","Society","Some text of news","http://url 3"));
    }

    public void add(SingleNews news){
        newsList.add(news);
    }
    public List<SingleNews> getAll(){
        return newsList;
    }
    public SingleNews getById(Integer id){
        for (SingleNews singleNews : newsList) {
            if(singleNews.getId().equals(id)){
                return singleNews;
            }
        }
        return null;
    }
    //-------------------------
    public boolean contains(SingleNews singleNews){
        return newsList.contains(singleNews);
    }

    public boolean containsById(SingleNews singleNews){
        for (SingleNews currentNews : newsList) {
            if(currentNews.getId().equals(singleNews.getId())){
                return true;
            }
        }
        return false;
    }
    public void delete(SingleNews singleNews){
        newsList.remove(singleNews);
    }

    public boolean delete(Integer id){ //переписати не можна видаляти в ытераторы
        Iterator<SingleNews> iter = newsList.iterator();
        while (iter.hasNext()) {
            SingleNews singleNews= iter.next();
            if(singleNews.getId().equals(id)){
                iter.remove();
                return true;
            }
        }
        return false;
    }

    public void update(SingleNews oldSingleNews,SingleNews newSingleNews){ //переписати
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
    }



    public List<SingleNews> getAllByName(String name){//перейминувати
        List<SingleNews> list = new ArrayList<>();
        for (SingleNews singleNews : newsList) {
            if(singleNews.getTitle().equals(name)){
                list.add(singleNews);
            }
        }
        return list;
    }

    public List<SingleNews> getAllByAuthor(String category){//перейменувати на катерогію
        List<SingleNews> list = new ArrayList<>();
        for (SingleNews singleNews : newsList) {
            if(singleNews.getCategory().equals(category)){
                list.add(singleNews);
            }
        }
        return list;
    }

}

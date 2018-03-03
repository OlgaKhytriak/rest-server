package com.epam.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "news")
public class News {
    private Integer id;
    private String title;
    private String description;
    private String body;
    private String link;

    public News(Integer id, String title, String description, String body, String link) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.body = body;
        this.link = link;
    }

    public News() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        News news = (News) obj;
        if (this.id == news.id || this.title.equals(news.title)) {
            return true;
        }
        return false;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

package com.crawler;

import java.sql.SQLException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws SQLException {
        DB db = new DB();
        db.runSql2("TRUNCATE Article;");
        ArrayList<String> url = new ArrayList<>();
        for (int i =1;i<=10;i++){
            url.add("https://suckhoedoisong.vn/chinh-tri-c30/p"+i);
            url.add("https://suckhoedoisong.vn/y-te-c36/p"+i);
            url.add("https://suckhoedoisong.vn/van-ban-chinh-sach-c34/p"+i);
            url.add("https://suckhoedoisong.vn/phong-su-c33/p"+i);
            url.add("https://suckhoedoisong.vn/ban-doc-c24/p"+i);
            url.add("https://suckhoedoisong.vn/the-thao-c58/p"+i);
            url.add("https://suckhoedoisong.vn/the-gioi-sao-c54/p"+i);
        }
        CrawlerData crawlerData = new CrawlerData(url);
        crawlerData.jquery(url);
//        CrawlerImg crawler = new CrawlerImg(url);
//        crawler.saveFile(url);
    }
}

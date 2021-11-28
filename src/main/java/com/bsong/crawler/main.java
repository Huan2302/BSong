package com.bsong.crawler;

import java.sql.SQLException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws SQLException {
        DB db = new DB();
        db.runSql2("TRUNCATE songs;");
        ArrayList<String> url = new ArrayList<>();
        for (int i =1;i<=5;i++){
            url.add("https://www.nhaccuatui.com/bai-hat/thieu-nhi-moi."+i+".html");
            url.add("https://www.nhaccuatui.com/bai-hat/tien-chien-moi."+i+".html");
            url.add("https://www.nhaccuatui.com/bai-hat/nhac-tre-moi."+i+".html");
            url.add("https://www.nhaccuatui.com/bai-hat/tru-tinh-moi."+i+".html");
            url.add("https://www.nhaccuatui.com/bai-hat/remix-viet-moi."+i+".html");
            url.add("https://www.nhaccuatui.com/bai-hat/pop-moi."+i+".html");
            url.add("https://www.nhaccuatui.com/bai-hat/country-moi."+i+".html");
            url.add("https://www.nhaccuatui.com/bai-hat/nhac-han-moi."+i+".html");
            url.add("https://www.nhaccuatui.com/bai-hat/nhac-thai-moi."+i+".html");
        }
        CrawlerData crawlerData = new CrawlerData(url);
        crawlerData.jquery(url);
//        CrawlerImg crawler = new CrawlerImg(url);
//        crawler.saveFile(url);
    }
}

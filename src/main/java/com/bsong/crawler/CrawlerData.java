package com.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CrawlerData {
    ArrayList<String> url;

    public CrawlerData(ArrayList<String> url) {
        this.url = url;
    }

    public static DB db = new DB();

    private ArrayList<Web> processPage(ArrayList<String> url){
        ArrayList<Web> list = new ArrayList<>();

        for (int i =0;i<url.size();i++){
            Document doc = null;
            try {
                doc = Jsoup.connect(url.get(i)).get();
                Elements elements = doc.getElementsByClass("desc_list_news_home");
                for (Element e : elements) {
                    Web item = new Web();

                    item.setTitle(e.getElementsByTag("a").text());
                    item.setDetailURL(e.getElementsByTag("a").attr("href"));
                    String category = e.getElementsByClass("cl_green").text();

                    if (category.equals("Chính trị")){
                        item.setCategories(1);
                    }else if(category.equals("Y tế")){
                        item.setCategories(2);
                    }else if(category.equals("Văn bản - chính sách")){
                        item.setCategories(3);
                    }else if(category.equals("Phóng sự")){
                        item.setCategories(4);
                    }else if(category.equals("Bạn đọc")){
                        item.setCategories(5);
                    }else if(category.equals("Thể thao")){
                        item.setCategories(6);
                        }else if(category.equals("Thế giới sao")){
                        item.setCategories(7);
                    }

                    Document doc2 = Jsoup.connect(item.getDetailURL()).get();

                    Elements elements2 = doc2.getElementsByClass("col660 m-auto mb40");
                    for (Element e2 :elements2){
                        item.setShortDescription(e2.getElementsByClass("sapo_detail").text());
                        item.setContent(e2.getElementById("content_detail_news").html());

                        //set date
                        String date= e2.getElementsByClass("post-time").text().substring(6,16);
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        String date2 = simpleDateFormat.format(date1);
                        item.setCreateDate(date2);
                    }
                    list.add(item);
                }
            } catch (IOException | ParseException e) {
                e.getMessage();
            }
        }
        return list;
    }

    public void jquery(ArrayList<String> urls){
        ArrayList<Web> list = processPage(urls);
        for (int i =0;i<list.size();i++){
            try {
                PreparedStatement stmt = null;
                String sql = "INSERT INTO `baomoi`.`Article`" + " (`content`, `createBy`, `createDate`, `highlight`, " +
                        "`photo`, `shortDescription`, slug, `title`,`view`,`category_id`) " +
                        "VALUES" + " (?, ?, ?, ?, ?, ?, ?,?,?,?);";
                stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, list.get(i).getContent());
                stmt.setString(2, "Vo Tuong Huan");
                stmt.setString(3, list.get(i).getCreateDate());
                stmt.setInt(4, 1);
                stmt.setString(5, i+1+ ".jpg");
                stmt.setString(6, list.get(i).getShortDescription());
                stmt.setString(7, list.get(i).getDetailURL());
                stmt.setString(8, list.get(i).getTitle());
                stmt.setInt(9, 1);
                stmt.setInt(10, list.get(i).getCategories());
                stmt.execute();
            } catch (SQLException e) {
                e.getMessage();
            }

        }
    }
}

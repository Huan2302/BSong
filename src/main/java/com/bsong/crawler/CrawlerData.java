package com.bsong.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

                Elements elements = doc.getElementsByClass("info_song");
                for (Element e : elements) {
                    Web item = new Web();
                    item.setName(e.getElementsByClass("name_song").text());
                    item.setPreview_text(e.getElementsByClass("name_song").attr("href"));
                    Document doc2 = Jsoup.connect(item.getPreview_text()).get();
                    Element elements2 = doc2.getElementById("divLyric");
                    item.setDetail_text(elements2.html());

                    Elements e_cat = doc.getElementsByClass("nomore");
                    String cat_name = e_cat.text();
                    if (cat_name.equals("Bài Hát Thiếu Nhi BXH Bài hát")){
                        item.setCat_id(1);
                    }else if (cat_name.equals("Bài Hát Tiền Chiến BXH Bài hát")){
                        item.setCat_id(2);
                    }else if (cat_name.equals("Bài Hát Nhạc Trẻ BXH Bài hát")){
                        item.setCat_id(3);
                    }else if (cat_name.equals("Bài Hát Trữ Tình BXH Bài hát")){
                        item.setCat_id(4);
                    }else if (cat_name.equals("Bài Hát Remix Việt BXH Bài hát")){
                        item.setCat_id(5);
                    }else if (cat_name.equals("Bài Hát Pop BXH Bài hát")){
                        item.setCat_id(6);
                    }else if (cat_name.equals("Bài Hát Country BXH Bài hát")){
                        item.setCat_id(7);
                    }else if (cat_name.equals("Bài Hát Nhạc Hàn BXH Bài hát")){
                        item.setCat_id(8);
                    }else if (cat_name.equals("Bài Hát Nhạc Thái BXH Bài hát")){
                        item.setCat_id(9);
                    }

                    list.add(item);
                }
            } catch (IOException e) {
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
                String sql = "INSERT INTO `bsong`.`songs`" + " (`name`, `preview_text`, `detail_text`, `date_create`, " +
                        "`picture`, `counter`,`cat_id`)" +
                        "VALUES" + " (?, ?, ?, ?, ?, ?, ?);";
//                String sql = "insert into bsong(name,preview_text,detail_text,date_create,picture,counter,cat_id) values  (?,?,?,?,?,?,?)";
                stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1,list.get(i).getName());
                stmt.setString(2,list.get(i).getPreview_text());
                stmt.setString(3,list.get(i).getDetail_text());
                stmt.setString(4,"2021-08-08 02:22:52");
                stmt.setString(5,i+1+".jpg");
                stmt.setInt(6,0);
                stmt.setInt(7,list.get(i).getCat_id());
                stmt.execute();
            } catch (SQLException e) {
                e.getMessage();
            }

        }
    }
}

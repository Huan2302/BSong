package com.bsong.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

public class CrawlerImg {
    ArrayList<String> url;

    public CrawlerImg(ArrayList<String> url) {
        this.url = url;
    }

    private ArrayList<String> listImg(ArrayList<String> URL) throws IOException {
        ArrayList<String> list_img = new ArrayList<>();
        for (int i=1;i<URL.size();i++){
            Document document = Jsoup.connect(URL.get(i)).get();
            Elements elements = document.getElementsByClass("avatar_song");
            for (Element e : elements){
                String url = e.child(0).attr("src");
                if (url.equals("")) {
                    continue;
                }
                list_img.add(url);
            }
        }
        return list_img;
    }

    private void savaImg(String src,String name){
        try {
            URL url = new URL(src);
            InputStream in = url.openStream();
            OutputStream out = new FileOutputStream("/Users/admin/Desktop/Java/Hoc/BSong/src/main/webapp/teamplate/admin/assets/img" + "/" +name);
            for(int b; (b = in.read()) != -1;){
                out.write(b);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Can not Dowload File !");
        }
    }

    public void saveFile(ArrayList<String> url) {
        try {
            ArrayList<String> list_img = listImg(url);
            for (int i = 0; i<= list_img.size();i++){
                String name = i + ".jpg";
                savaImg(list_img.get(i),name);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error", "Error to save file !", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Dowload sucessfull chap " + url);
    }
}

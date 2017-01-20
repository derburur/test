package org.androidtown.rss;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Handler handler = new Handler();
    RssAdapter rssAdapter;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);

        rssAdapter = new RssAdapter();
        listView.setAdapter(rssAdapter);
    }

    class RssAdapter extends BaseAdapter {

        ArrayList<RssItem> itemList = new ArrayList<RssItem>();
        public void setItemList(ArrayList<RssItem> itemList){
            this.itemList = itemList;
        }
        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RssItemView view = null;
            if(convertView == null){
                view = new RssItemView(getApplicationContext());
            } else {
                view = (RssItemView) convertView;
            }
            view.setRssItem(itemList.get(position));
            return null;
        }
    }


    public void onButton1Clicked(View v){
        RequestThread thread = new RequestThread();
        thread.start();
    }

    class RequestThread extends Thread {
        public void run(){
            try{
                String urlStr = editText.getText().toString();
                println("요청한 URL : " + urlStr);
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setConnectTimeout(15000);

                int resCode = conn.getResponseCode();
                println("응답 코드 : " + resCode);
                if (resCode == HttpURLConnection.HTTP_OK){
                    InputStream instream = conn.getInputStream();
                    parseRSS(instream);
                    }

                                        conn.disconnect();
                } else {
                    System.out.println("응답코드 : " + resCode);
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }


    private void parseRSS(InputStream instream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(instream);
        final ArrayList<RssItem> itemList = parseDocument(document);
        println("파싱된 아이템의 갯수 : " + itemList.size());

        handler.post(new Runnable() {
            @Override
            public void run() {
                rssAdapter.setItemList(itemList);
                rssAdapter.notifyDataSetChanged();
            }
        });


    }

    private ArrayList<RssItem> parseDocument(Document document){
        Element elem = document.getDocumentElement();
        NodeList nodeList = elem.getElementsByTagName("item");

        ArrayList<RssItem> itemList = new ArrayList<RssItem>();
        if(nodeList != null && nodeList.getLength() > 0) {

            for (int i = 0; i < nodeList.getLength(); i++){
                RssItem item = parseItemNode(nodeList, i);
                itemList.add(item);
            }
        }
        return itemList;
    }

    private RssItem parseItemNode(NodeList nodeList, int index){
        Element elem = nodeList.item(index);
        Element titleElem = elem.getElementsByTagName("title").item(0);
        Element linkElem = elem.getElementsByTagName("link").item(0);
        Element descriptionElem = elem.getElementsByTagName("description").item(0);
        Element dcDateElem = elem.getElementsByTagName("dc:date").item(0);
        Element imageElem = elem.getElementsByTagName("image").item(0);

        String title = "";
        if (titleElem != null){
            Node firstChild = titleElem.getFirstChild();
            if(firstChild != null){
                title = firstChild.getNodeValue();
            }
        }

        String link = "";
        if (linkElem != null){
            Node firstChild = linkElem.getFirstChild();
            if(firstChild != null){
                link = firstChild.getNodeValue();
            }
        }

        String description = "";
        if (descriptionElem != null){
            Node firstChild = descriptionElem.getFirstChild();
            if(firstChild != null){
                description = firstChild.getNodeValue();
            }
        }

        String dcDate = "";
        if (dcDateElem != null){
            Node firstChild = dcDateElem.getFirstChild();
            if(firstChild != null){
                dcDate = firstChild.getNodeValue();
            }
        }

        String image = "";
        if (imageElem != null){
            Node firstChild = imageElem.getFirstChild();
            if(firstChild != null){
                image = firstChild.getNodeValue();
            }
        }

        RssItem item = new RssItem(title, link, description, image, dcDate);

        return item;
    }

    private void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("");
                textView.append(data + "\n");
            }
        });

    }
}

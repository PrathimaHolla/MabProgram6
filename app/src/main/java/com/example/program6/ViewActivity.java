package com.example.program6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ViewActivity extends AppCompatActivity {
    TextView lblXmlData, lblJsonData;
    int mode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lblJsonData=(TextView)findViewById(R.id.jsonLabel);
        lblXmlData=(TextView)findViewById(R.id.xmlLabel);
        mode=getIntent().getIntExtra("mode",0);
        if(mode==1)
            parseJson();
        else
            parseXmlDocument();

    }
    public void parseXmlDocument()
    {
        try{
            InputStream is=getAssets().open("input.xml");

            DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
            Document doc=dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList=doc.getElementsByTagName("employee");
            for(int i=0;i<nList.getLength();i++){
                Node node=nList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element element2=(Element)node;
                    lblXmlData.setText("City Name: " + getValue("city_name", element2) + "\n");
                    lblXmlData.append("Latitude: " + getValue("Latitude", element2) + "\n");
                    lblXmlData.append("Longitude: " + getValue("Longitude", element2) + "\n");
                    lblXmlData.append("Temperature: " + getValue("Temperature", element2) + "\n");
                    lblXmlData.append("Humidity: " + getValue("Humidity", element2) + "\n");

                }


            }

        }
        catch(Exception e){
            e.printStackTrace();

        }

    }
    private static String getValue(String tag, Element element)
    {
        NodeList nodelist=element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node=nodelist.item(0);
        return node.getNodeValue();
    }

    public void parseJson()
    {
        try{
            InputStream inputStream=getAssets().open("input.json");
            byte[] data=new byte[inputStream.available()];
            inputStream.read(data);

            String readdata=new String(data);
            JSONObject jsonObject=new JSONObject(readdata);
            JSONObject jsonObject1=jsonObject.getJSONObject("employee");

            lblJsonData.setText("City Name: " + jsonObject1.getString("city_name") + "\n");
            lblJsonData.append("Latitude: " + jsonObject1.getString("Latitude") + "\n");
            lblJsonData.append("Longitude: " + jsonObject1.getString("Longitude") + "\n");
            lblJsonData.append("Temperature: " + jsonObject1.getString("Temperature") + "\n");
            lblJsonData.append("Humidity: " + jsonObject1.getString("Humidity") + "\n");






        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
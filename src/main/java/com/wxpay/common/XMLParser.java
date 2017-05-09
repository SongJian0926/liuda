package com.wxpay.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jodd.util.StringUtil;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * User: rizenguo
 * Date: 2014/11/1
 * Time: 14:06
 */
public class XMLParser {

    public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {

        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is =  Util.getStringStream(xmlString);
        Document document = builder.parse(is);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;

    }
    
    public static Map<String,Object> getMapFromXML_WxRefund(String xmlStr) throws ParserConfigurationException, IOException, SAXException {
    	if(StringUtil.isEmpty(xmlStr)) {  
            return null;  
        }  
        Map<String, Object> map = new HashMap<String, Object>();  
        //将xml格式的字符串转换成Document对象  
        org.dom4j.Document doc;
		try {
			doc = DocumentHelper.parseText(xmlStr);
	        //获取根节点  
			org.dom4j.Element root = doc.getRootElement();  
	        //获取根节点下的所有元素  
	        List children = root.elements();  
	        //循环所有子元素  
	        if(children != null && children.size() > 0) {  
	            for(int i = 0; i < children.size(); i++) {  
	            	org.dom4j.Element child = (org.dom4j.Element)children.get(i);  
	                map.put(child.getName(), child.getTextTrim());  
	            }  
	        }  
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
        return map;  
    }
}
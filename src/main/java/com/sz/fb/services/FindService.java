package com.sz.fb.services;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FindService {
	private static final String HREF_ATTRIBUTE = "href";
	private static final String CLASS_ATTRIBUTE = "class";
	private static final String LI_TAG = "LI";
	private static final String USER_SELECTED_PARAM = "user selected";
	private Document document;

	public FindService(Document document) {
		super();
		this.document = document;
	}
	
	public String getUserLink(){
		if(document != null){
			NodeList nodeList = document.getElementsByTagName(LI_TAG);
			for (int i = 0; i < nodeList.getLength(); ++i) {
				Node node =  nodeList.item(i);
				NamedNodeMap attribute = node.getAttributes();
				Node attributeValue = attribute.getNamedItem(CLASS_ATTRIBUTE);
				if(attributeValue != null && USER_SELECTED_PARAM.equals(attributeValue.getTextContent())) {
					NodeList childNodes = node.getChildNodes();
					for (int j = 0; j < childNodes.getLength(); j++) {
						Node nodeLink = childNodes.item(j).getAttributes().getNamedItem(HREF_ATTRIBUTE);
						return nodeLink.getTextContent();
					}
				}
			}
		}
		return "";
	}
}

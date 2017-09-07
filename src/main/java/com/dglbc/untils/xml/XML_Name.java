package com.dglbc.untils.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

@Component
public class XML_Name {
	private List<Element> elementList =new ArrayList<Element>();
	public  List<Element> findByName(Element node,String attributetype,String name){
		List<Element> elementListTemp =new ArrayList<Element>();
		findByNameUnit(node,attributetype,name);
		elementListTemp.addAll(elementList);
		elementList.clear();
		return elementListTemp;
	}
	
	public  void findByNameUnit(Element node,String attributetype, String name) {
		
		if (isElementValueEquails(node,attributetype, name)) {
			elementList.add(node);
			
		} 
		@SuppressWarnings("unchecked")
		Iterator<Element> it = node.elementIterator();
		// 遍历
		while (it.hasNext()) {
			// 获取某个子节点对象
			Element element = it.next();
			// 对子节点进行遍历
			findByNameUnit(element,attributetype, name);			
		}
		
	}
	@SuppressWarnings("finally")
	public  boolean isElementValueEquails(Element node, String attributeName, String value) {
		boolean resultCode = false;
		try {
			String xidNode = node.attribute(attributeName).getValue();
			if (xidNode.equals(value)) {
				resultCode = true;
			}

		} finally {
			return resultCode;
		}
	}
}

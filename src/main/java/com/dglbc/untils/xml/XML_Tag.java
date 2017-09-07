package com.dglbc.untils.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

@Component
public class XML_Tag {
	private List<Element> elementTagList = new ArrayList<Element>();

	public List<Element> findByTag(Element node, String tagName) {
		List<Element> elementListTemp = new ArrayList<Element>();
		findByTagUnit(node, tagName);
		elementListTemp.addAll(elementTagList);
		elementTagList.clear();
		return elementListTemp;
	}

	public void findByTagUnit(Element node, String tagName) {
		if (isElementTagEquails(node, tagName)) {
			elementTagList.add(node);
		}
		@SuppressWarnings("unchecked")
		Iterator<Element> it = node.elementIterator();
		// 遍历
		while (it.hasNext()) {
			// 获取某个子节点对象
			Element element = it.next();
			// 对子节点进行遍历
			findByTagUnit(element, tagName);
		}
	}

	public boolean isElementTagEquails(Element node, String tagName) {
		boolean resultCode = false;
		if (node.getName().equals(tagName)) {
			resultCode = true;
		}
		return resultCode;
	}
}

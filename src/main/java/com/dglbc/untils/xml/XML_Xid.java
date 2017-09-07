package com.dglbc.untils.xml;

import java.util.Iterator;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

@Component
public class XML_Xid {
	public  Element findByXid(Element node, String xid) {
		Element elementTemp = null;
		if (isElementValueEquails(node, "xid", xid)) {
			elementTemp = node;

		} else {
			@SuppressWarnings("unchecked")
			Iterator<Element> it = node.elementIterator();
			// 遍历
			while (it.hasNext()) {
				// 获取某个子节点对象
				Element element = it.next();
				// 对子节点进行遍历
				if (findByXid(element, xid) != null) {
					elementTemp = findByXid(element, xid);
					break;
				} ;
			}
		}
		return elementTemp;
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

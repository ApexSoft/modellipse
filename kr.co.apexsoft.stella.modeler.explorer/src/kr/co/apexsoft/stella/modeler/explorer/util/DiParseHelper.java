package kr.co.apexsoft.stella.modeler.explorer.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.resources.IFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DiParseHelper {
	
	private IFile _file;
	private Document _diDocument;
	private final String XPATH_RESOURCE_URL = "//children[@resourceURL]";
	private final String RESOURCE_URL = "resourceURL";

	public boolean checkControlled(IFile file) {
		boolean isControlled = false;
		_file = file;
		setDiDocument(_file);
		try {
			String fileName = file.getName();
			NodeList nodeList = getNodeList(XPATH_RESOURCE_URL);
			
			for (int i=0 ; i<nodeList.getLength() ; i++) {
				Node aNode = nodeList.item(i);
				String resourceName = aNode.getAttributes().getNamedItem(RESOURCE_URL).getTextContent();
				
				System.out.println("fileName : " + fileName);
				System.out.println("resourceName : " + resourceName);
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void setDiDocument(IFile file) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();		
	        InputSource is = new InputSource(new FileReader(file.getLocationURI().getPath()));
	        _diDocument = builder.parse(is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("DiParseHelper.setDiDocument, line : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			System.out.println("파일이 없습니다");
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private NodeList getNodeList(String XPathString) throws XPathExpressionException {
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		NodeList nodeList = (NodeList)xpath.evaluate(XPathString, _diDocument, XPathConstants.NODESET);
		return nodeList;
	}
	
	
}

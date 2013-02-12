package kr.co.apexsoft.stella.modeler.explorer.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ApexDiParseHelper {
	
	private static final String XPATH_RESOURCE_URL = "//children[@resourceURL]";
	private static final String RESOURCE_URL = "resourceURL";
	
	/**
	 * apex added
	 * 
	 * Control된 리소스가 탐색기에 보이지 않도록 Di 파일 파싱하여
	 * Control된 di 파일을 반환
	 * 
	 * @param project
	 * @return control된 di 파일 리스트
	 */
	public static List<IFile> getControlledDiFiles(IProject project) {
		
		List<IFile> controlledDiFiles = new ArrayList<IFile>();
		List<String> controlledDiFileNames = new ArrayList<String>();
		IResource[] members = null;
		
		try {
			members = project.members();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( members != null ) {

			for ( IResource r : members ) {

				if ( r instanceof IFile ) {
					IFile file = (IFile)r;

					if(OneFileUtils.isDi(file)) {
						controlledDiFileNames.addAll(getControlledFileNames(file));													
					}
				} 
			}	
		}
		
		if ( members != null ) {

			for ( IResource r : members ) {

				if ( r instanceof IFile ) {
					IFile file = (IFile)r;					

					if(OneFileUtils.isDi(file)) {
						String fileName = r.getName();
						String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
						
						if (controlledDiFileNames.contains(fileNameWithoutExtension) ) {
							controlledDiFiles.add(file);
						}	
					}
				} 
			}	
		}
		
		return controlledDiFiles;
	}

	/**
	 * apex added
	 * 
	 * di 파일을 인수로 받아 그 di 파일을 파싱하여 children 노드가 있는 지 검사하고
	 * 있는 경우 확장자를 제외한 파일명 리스트를 반환 
	 * 
	 * @param file Di 파일
	 * @return control된 자원의 확장자를 제외한 파일명 리스트
	 */
	private static List<String> getControlledFileNames(IFile file) {
		
		Document diDocument = setDiDocument(file);
		List<String> umlFileNames = new ArrayList<String>();
		List<String> notationFileNames = new ArrayList<String>();
		List<String> controlledDiFileNames = new ArrayList<String>();
		
		try {
			NodeList nodeList = getNodeList(diDocument, XPATH_RESOURCE_URL);
			
			for (int i=0 ; i<nodeList.getLength() ; i++) {
				Node aNode = nodeList.item(i);
				String resourceName = aNode.getAttributes().getNamedItem(RESOURCE_URL).getTextContent();
				
				if ( resourceName.endsWith("uml") ) {
					umlFileNames.add(resourceName);
				} else if ( resourceName.endsWith("notation") ) {
					notationFileNames.add(resourceName);
				}
				
			}
			
			if ( umlFileNames.size() == notationFileNames.size() ) {
				
				for ( String controlledFileName : umlFileNames ) {
					controlledDiFileNames.add(controlledFileName.substring(0, controlledFileName.lastIndexOf('.')));					
				}
			}			
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return controlledDiFileNames;
	}
	
	/**
	 * apex added
	 * 
	 * di 파일을 인수로 받아 파싱을 위한 Document 반환
	 * 
	 * @param file di 파일
	 * @return XML 파싱을 위한 Document
	 */
	public static Document setDiDocument(IFile file) {		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
		try {
			builder = factory.newDocumentBuilder();		
	        InputSource is = new InputSource(new FileReader(file.getLocationURI().getPath()));
	        document = builder.parse(is);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("DiParseHelper.setDiDocument, line : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
			System.out.println("파일이 없습니다");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	private static NodeList getNodeList(Document diDocument, String XPathString) throws XPathExpressionException {
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		NodeList nodeList = (NodeList)xpath.evaluate(XPathString, diDocument, XPathConstants.NODESET);
		return nodeList;
	}
	
	
}

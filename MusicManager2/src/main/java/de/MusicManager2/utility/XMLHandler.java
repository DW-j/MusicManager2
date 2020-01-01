package de.MusicManager2.utility;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.MusicManager2.elements.Songlist;

public class XMLHandler {
	
	private static File file = new File("C:\\Users\\wittn\\workspaces\\local workspace\\MusicManager2\\src\\main\\java\\files\\music.xml");
	
	public static void writeXML(Songlist songlist) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Songlist.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(songlist, file);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static Songlist readXML() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Songlist.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    return (Songlist) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e1) {
			e1.printStackTrace();
			return null;
		}	
	}
	
}
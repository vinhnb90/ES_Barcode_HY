package esolutions.com.barcodehungyenpc.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyXMLHandler extends DefaultHandler {

    Boolean currentElement = false;
    String currentValue = null;
    public static ProgramList programList = null;

    public static ProgramList getProgramList() {
        return programList;
    }

    public static void setProgramList(ProgramList programList) {
        MyXMLHandler.programList = programList;
    }

    //called when tag starts ( ex:- <tblPrograms>diffgr:id="tblPrograms1" msdata:rowOrder="0"</tblPrograms>  -- <tblPrograms> )
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        currentElement = true;

        //set up for hierarchy scan to place data within category
        if (localName.equals("DataTable1"))
        {
            /** Start */ 
            programList = new ProgramList();
        } else if (localName.equals("tblPrograms")){//"DataTable1" - DOES NOT WORK - F.C.
            /** Get attribute value */
            String attr = attributes.getValue(0);
            String attr2 = attributes.getValue(1);
            programList.setTable(attr);
            programList.setRowOrder(attr2);
        }

    }

    //called when tag closing ( ex:- <Program>Ancillary</Program>  -- </Program> )
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        currentElement = false;

        /** set value */ 
        if (localName.equalsIgnoreCase("Program"))
            programList.setProgram(currentValue);
        //else if (localName.equalsIgnoreCase("tblPrograms"))
            //programList.setWebsite(currentValue);

    }

    //called to get tag characters ( ex:- <Program>Ancillary</Program> -- to get Ancillary character )
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (currentElement) {
            currentValue = new String(ch, start, length);
            currentElement = false;
        }

    }

}
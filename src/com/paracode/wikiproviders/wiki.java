/*
    Copyright (C) 2009 Nahum J. Dotan (dotan@paracode.com)
    
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.paracode.wikiproviders;

import java.util.*;

import redstone.xmlrpc.XmlRpcFault;

public interface wiki
{

        Hashtable getRecentChanges(Date since) throws XmlRpcFault;
        
        Integer getRPCVersionSupported() throws XmlRpcFault;
        
        String getPage(String pagename) throws XmlRpcFault;
        String getPage(String pagename, Integer version) throws XmlRpcFault;
        
        String getPageVersion(String pagename) throws XmlRpcFault;
        String getPageVersion(String pagename, Integer version)throws XmlRpcFault;
        
        String getPageHTML(String pagename) throws XmlRpcFault;
        String getPageHTML(String pagename, Integer version) throws XmlRpcFault;
        
        String getPageHTMLVersion(String pagename) throws XmlRpcFault;
        String getPageHTMLVersion(String pagename, Integer version) throws XmlRpcFault;
                
        Vector getAllPages() throws XmlRpcFault;
        
        Hashtable getPageInfo(String pagename) throws XmlRpcFault;
        Hashtable getPageInfo(String pagename, Integer version) throws XmlRpcFault;
        
        Hashtable getPageInfoVersion(String pagename) throws XmlRpcFault;
        Hashtable getPageInfoVersion(String pagename, Integer version) throws XmlRpcFault;
                
        Boolean putPage(String pagename, String content, Hashtable attributes) throws XmlRpcFault;
        
        Hashtable istAttachments(String pagename) throws XmlRpcFault;
        
        byte[] getAttachment(String path) throws XmlRpcFault;
        
        Boolean putAttachment(String path, byte[] data) throws XmlRpcFault;
        
        Boolean putAttachmentEx(String pagename, String filename, String description, byte[] data) throws XmlRpcFault;
        Boolean putAttachmentEx(String pagename, String filename, String description, byte[] data, boolean replace) throws XmlRpcFault;
        
        Boolean deletePage(String name) throws XmlRpcFault;
        Boolean deletePage(String name, int version) throws XmlRpcFault;
        
        Boolean deleteAttachment(String path) throws XmlRpcFault;
        
        Vector listLinks(String pagename) throws XmlRpcFault;
        String wikiToHtml(String text) throws XmlRpcFault;
}

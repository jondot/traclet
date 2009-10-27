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

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;



public interface WikiProxy
{
    Hashtable getRecentChanges(Date since) ;
    
    Integer getRPCVersionSupported() ;
    
    String getPage(String pagename) ;
    String getPage(String pagename, Integer version) ;
    
    String getPageVersion(String pagename) ;
    String getPageVersion(String pagename, Integer version);
    
    String getPageHTML(String pagename);
    String getPageHTML(String pagename, Integer version);
    
    String getPageHTMLVersion(String pagename) ;
    String getPageHTMLVersion(String pagename, Integer version) ;
            
    Vector getAllPages() ;
    
    Hashtable getPageInfo(String pagename) ;
    Hashtable getPageInfo(String pagename, Integer version) ;
    
    Hashtable getPageInfoVersion(String pagename) ;
    Hashtable getPageInfoVersion(String pagename, Integer version) ;
            
    Boolean putPage(String pagename, String content, Hashtable attributes) ;
    Hashtable istAttachments(String pagename) ;
    
    byte[] getAttachment(String path) ;
    
    Boolean putAttachment(String path, byte[] data) ;
    
    Boolean putAttachmentEx(String pagename, String filename, String description, byte[] data) ;
    Boolean putAttachmentEx(String pagename, String filename, String description, byte[] data, boolean replace) ;
    
    Boolean deletePage(String name) ;
    Boolean deletePage(String name, int version) ;
    
    Boolean deleteAttachment(String path) ;
    
    Vector listLinks(String pagename) ;
    String wikiToHtml(String text) ;
}

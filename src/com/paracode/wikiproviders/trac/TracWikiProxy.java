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

package com.paracode.wikiproviders.trac;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import redstone.xmlrpc.XmlRpcFault;
import redstone.xmlrpc.XmlRpcProxy;

import com.paracode.wikiproviders.WikiProxy;
import com.paracode.wikiproviders.wiki;

public class TracWikiProxy implements WikiProxy
{
    wiki _rpcWiki;
    
    public TracWikiProxy(String url) throws MalformedURLException
    {
        _rpcWiki = ( wiki ) XmlRpcProxy.createProxy(new URL(url), new Class[] { wiki.class }, false );
    }
    @Override
    public Boolean deleteAttachment(String path)
    {
        try
        {
            return _rpcWiki.deleteAttachment(path);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deletePage(String name)
    {
        try
        {
            return _rpcWiki.deletePage(name);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean deletePage(String name, int version)
    {
        try
        {
            return _rpcWiki.deletePage(name,version);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Vector getAllPages()
    {
        try
        {
            return _rpcWiki.getAllPages();
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public byte[] getAttachment(String path)
    {
        try
        {
            return _rpcWiki.getAttachment(path);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPage(String pagename)
    {
        try
        {
            return _rpcWiki.getPage(pagename);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPage(String pagename, Integer getPage)
    {
        try
        {
            return _rpcWiki.getPage(pagename,getPage);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPageHTML(String pagename)
    {
        try
        {
            return _rpcWiki.getPageHTML(pagename);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPageHTML(String pagename, Integer version)
    {
        try
        {
            return _rpcWiki.getPageHTML(pagename,version);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPageHTMLVersion(String pagename)
    {
        try
        {
            return _rpcWiki.getPageHTMLVersion(pagename );
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPageHTMLVersion(String pagename, Integer version)
    {
        try
        {
            return _rpcWiki.getPageHTMLVersion(pagename,version );
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Hashtable getPageInfo(String pagename)
    {
        try
        {
            return _rpcWiki.getPageInfo(pagename );
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Hashtable getPageInfo(String pagename, Integer version)
    {
        try
        {
            return _rpcWiki.getPageInfo(pagename,version );
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Hashtable getPageInfoVersion(String pagename)
    {
        try
        {
            return _rpcWiki.getPageInfoVersion(pagename );
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Hashtable getPageInfoVersion(String pagename, Integer version)
    {
        try
        {
            return _rpcWiki.getPageInfoVersion(pagename,version );
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPageVersion(String pagename)
    {
        try
        {
            return _rpcWiki.getPageVersion(pagename );
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getPageVersion(String pagename, Integer version)
    {
        try
        {
            return _rpcWiki.getPageVersion(pagename,version );
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer getRPCVersionSupported()
    {
        try
        {
            return _rpcWiki.getRPCVersionSupported();
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Hashtable getRecentChanges(Date since)
    {
        try
        {
            return _rpcWiki.getRecentChanges(since);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Hashtable istAttachments(String pagename)
    {
        try
        {
            return _rpcWiki.istAttachments(pagename);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Vector listLinks(String pagename)
    {
        try
        {
            return _rpcWiki.listLinks(pagename);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean putAttachment(String path, byte[] data)
    {
        try
        {
            return _rpcWiki.putAttachment(path,data);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean putAttachmentEx(String pagename, String filename,
            String description, byte[] data)
    {
        try
        {
            return _rpcWiki.putAttachmentEx(pagename,filename,description,data);
        }
        catch (XmlRpcFault e)
        {
            
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean putAttachmentEx(String pagename, String filename,
            String description, byte[] data, boolean replace)
    {
        try
        {
            return _rpcWiki.putAttachmentEx(pagename,filename,description,data,replace);
        }
        catch (XmlRpcFault e)
        {

            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean putPage(String pagename, String content, Hashtable attributes)
    {
        try
        {
            System.out.println("[xml-rpc] wiki.putPage: "+pagename+" ("+content.length()+" chars)");
            
            return _rpcWiki.putPage(pagename,content,attributes);
        }
        catch (XmlRpcFault e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String wikiToHtml(String text)
    {
        try
        {
            return _rpcWiki.wikiToHtml(text);
        }
        catch (XmlRpcFault e)
        {
            e.printStackTrace();
        }
        return null;
    }

}

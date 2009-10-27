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

import com.paracode.wikiproviders.WikiDialect;
import com.paracode.wikiproviders.WikiProvider;
import com.paracode.wikiproviders.WikiProxy;
import com.paracode.wikiproviders.wiki;

import redstone.xmlrpc.XmlRpcProxy;

public class Trac implements WikiProvider
{
    public WikiProxy _wiki;
    private WikiDialect _dialect;
    
    public Trac(String url) throws MalformedURLException
    {
        _wiki = new TracWikiProxy(url);
        _dialect = new TracWikiDialect();
    }
    
    /* (non-Javadoc)
     * @see com.paracode.wikiproviders.trac.WikiProvider#getWikiDialect()
     */
    public WikiDialect getWikiDialect()
    {
        return _dialect;
    }
    
    /* (non-Javadoc)
     * @see com.paracode.wikiproviders.trac.WikiProvider#getWikiProxy()
     */
    public WikiProxy getWikiProxy()
    {
        return _wiki;
    }
}

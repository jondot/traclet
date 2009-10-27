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

package com.paracode;
import java.net.MalformedURLException;
import java.util.Hashtable;
import com.paracode.wikiproviders.trac.Trac;

public class WikiDocletRunner
{

    /**
     * @param args
     * @throws MalformedURLException 
     */
    public static void main(String[] args) throws MalformedURLException
    {
        Trac trac = new Trac("http://localhost:8000/learn/xmlrpc");
        trac.getWikiProxy().putPage("MyNewPage1", "hello world1", new Hashtable<String, String>());
        System.out.println("blerp");
    }
}



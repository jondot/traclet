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

import com.paracode.wikiproviders.BaseWikiDialect;

public class TracWikiDialect extends BaseWikiDialect
{
    public String h1(String text)
    {
        return "= "+text+" =\n";
    }
    
    public String h2(String text)
    {
        return "== "+text+" ==\n";
    }
    
    public String h3(String text)
    {
        return "=== "+text+" ===\n";
    }
    
    public String wikilink(String wikiTag, String title)
    {
        return "[wiki:"+wikiTag+" "+title+"]" ;
    }
    
    public String nl(String line)
    {
        return line+"[[BR]]\n";
    }

    public String b(String line)
    {
        return "'''"+line+"'''";
    }

    public String u(String line)
    {
        return "__"+line+"__";
    }   
    
    public String i(String line)
    {
        return "''"+line+"''";
    }
    
    public String p(String line)
    {
        return "";
    }
    
    public String pre(String text)
    {
        return "{{{\n"+text+"\n}}}";
    }

    @Override
    public Object sep()
    {
        return nl(nl(nl("")));
    }

    @Override
    public Object preEnd()
    {
        // TODO Auto-generated method stub
        return "\n}}}\n";
    }

    @Override
    public Object preStart()
    {
        // TODO Auto-generated method stub
        return "\n{{{\n";
    }
}


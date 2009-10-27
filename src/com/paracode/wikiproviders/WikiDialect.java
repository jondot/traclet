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

public interface WikiDialect
{
    public String h1(String text);
    
    public String h2(String text);
    
    public String h3(String text);
    
    public String wikilink(String wikiTag, String title);
    
    public String nl(String line);

    public String b(String line);

    public String u(String line);
    public String i(String line);
    
    public String p(String line);

    public String pre(String text);

    public Object sep();

    public Object preStart();

    public Object preEnd();
}

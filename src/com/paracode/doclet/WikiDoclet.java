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

package com.paracode.doclet;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import com.paracode.wikiproviders.WikiDialect;
import com.paracode.wikiproviders.WikiProvider;
import com.paracode.wikiproviders.trac.Trac;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.Doc;
import com.sun.javadoc.ExecutableMemberDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ProgramElementDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;

public class WikiDoclet
{

   private WikiProvider _wprovider;
   private WikiDialect _dia;
   
   
   public WikiDoclet(String url) throws FileNotFoundException, MalformedURLException
   {
       _wprovider = new Trac("http://localhost:8000/learn/xmlrpc");
       _dia = _wprovider.getWikiDialect();

   }

   public static int optionLength(String option)
   {
      if (option.equals("-wikiurl"))
      {
         return 1;
      }

      return 0;
   }

   public static boolean start(RootDoc rootDoc) throws FileNotFoundException, MalformedURLException
   {

      String options[][] = rootDoc.options();

      int optIdx;
      String url = "";
      
      for (optIdx = 0; optIdx < options.length; optIdx++)
      {
         if ( (options[optIdx][0].equals("-wikiurl")) && (options[optIdx].length > 1))
         {
            url = options[optIdx][1];
         }
         
      }

      url = "http://localhost:8000/learn/xmlrpc";
      System.out.println("Starting wiki doclet: wikiurl="+url); 
      WikiDoclet doclet = new WikiDoclet(url);

      if (rootDoc.specifiedClasses() != null)
      {
          System.out.println("process: by classes");    
          doclet.processByClasses(rootDoc);
      }
      
      if (rootDoc.specifiedPackages() != null)
        {
          System.out.println("process: by package");
          doclet.processByPackages(rootDoc);
        }

      return true;
   }

   public void processByPackages(RootDoc rootDoc)
   {
      PackageDoc packages[] = rootDoc.specifiedPackages();

      for (int pkgIndex = 0; pkgIndex < packages.length; pkgIndex++)
      {
         if ( isToBePrinted(packages[pkgIndex]) )
         {
            putPackagePage(packages[pkgIndex]); //TODO: put page - pkg name + class links

            ClassDoc classesP[] = packages[pkgIndex].allClasses();
            for (int classIndex = 0; classIndex < classesP.length; classIndex++)
            {
               putClassPage(classesP[classIndex]);
            }
         }
      }
   }

   public void putPackagePage(PackageDoc packageDoc)
   {
        StringBuilder page = new StringBuilder();
        page.append(_dia.nl("Package " + packageDoc.name())+"\n");
        renderComment(page, packageDoc, "package " + packageDoc.name());
        
        _wprovider.getWikiProxy().putPage(fullyQualifiedPackageName(packageDoc.name()), page.toString(), new Hashtable());
   }

   private String fullyQualifiedPackageName(String name)
   {
        return name;
   }

   public void processByClasses(RootDoc root)
   {
      ClassDoc classes[] = root.specifiedClasses();

      for (int j = 0; j < classes.length; j++)
      {
         if ( isToBePrinted(classes[j]) )
         {
            putClassPage(classes[j]);
         }
      }
   }

   public void putClassPage(ClassDoc clazz)
   {
      StringBuilder page =new StringBuilder();

      boolean isInterface = clazz.modifiers().endsWith("interface");
  

      page.append(_dia.h1(( isInterface ? "Interface" : "Class") + " " + clazz.name()));
      page.append(clazz.modifiers() + (isInterface ? " " : " class ") + _dia.b( clazz.name()));

      if (null != clazz.superclass())
      {
         if (clazz.superclass().toString().equals("java.lang.Object") == false)
         {
            page.append(" extends " + _dia.b(clazz.superclass().name()));
         }
      }

      ClassDoc implementations[] = clazz.interfaces();
      for (int impIdx = 0; impIdx < implementations.length; impIdx++)
      {
         if (impIdx == 0)
            page.append(" implements ");
         page.append(" " + implementations[impIdx].name() + " ");
         if (impIdx != implementations.length - 1)
             page.append(", ");
      }

      page.append(_dia.nl(""));

      page.append(_dia.nl(""));
      renderComment(page, clazz, "class " + clazz.name());
      page.append(_dia.nl(""));
      
      if (clazz.fields().length > 0)
      {
         page.append(_dia.nl(_dia.b("Fields")));
         FieldDoc fields[] = clazz.fields();
         boolean doSeparator = false;
         for (int fieldIdx = 0; fieldIdx < fields.length; fieldIdx++)
         {
            if ( isToBePrinted(fields[fieldIdx]) )
            {
               if (doSeparator)
               {
                  page.append(_dia.sep());
               }
               doSeparator = true;

               page.append(_dia.b(fields[fieldIdx].name()));


               renderElement(page, fields[fieldIdx]);
               renderComment(page, fields[fieldIdx], "method " + clazz.name() + "." + fields[fieldIdx].name());
            }
         }
         
         page.append(_dia.nl(""));
      }

      if (clazz.constructors().length > 0)
      {
         page.append(_dia.h2(" Constructors "));
         ConstructorDoc constructors[] = clazz.constructors();
         boolean doSeparator = false;
         for (int ctorIdx = 0; ctorIdx < constructors.length; ctorIdx++)
         {
            if ( isToBePrinted(constructors[ctorIdx]) )
            {
               if (doSeparator)
               {
                   page.append(_dia.sep());
               }
               doSeparator = true;
               
               renderElement(page, constructors[ctorIdx]);
               renderComment(page, constructors[ctorIdx], "method " + clazz.name() + "." + constructors[ctorIdx].name());
            }
         }
      }

      if (clazz.methods().length > 0)
      {
         page.append(_dia.h2(" Methods "));
         
         MethodDoc methods[] = clazz.methods();
         boolean doSeparator = false;
         for (int metIdx = 0; metIdx < methods.length; metIdx++)
         {
            if ( isToBePrinted(methods[metIdx]) )
            {
               if (doSeparator)
               {
                   page.append(_dia.sep());
               }
               doSeparator = true;

               renderElement(page, methods[metIdx]);
               renderComment(page, methods[metIdx], "method " + clazz.name() + "." + methods[metIdx].name());
            }
         }
         page.append(_dia.nl(""));
      }
      
      _wprovider.getWikiProxy().putPage(fullyQualifiedClassName(clazz.name()), page.toString(), new Hashtable());
   }
   
   private String fullyQualifiedClassName(String name)
   {
       return name;
   }

   public void renderComment(StringBuilder page, Doc document, String elementName)
   {
      log("renderComment: "+elementName);
      page.append(_dia.preStart());
      renderParagraph(page, document.commentText(), elementName);
      page.append(_dia.preEnd());
      renderParamTag(page,document, "@param", "Parameters", elementName + ":Parameters");
      renderTag(page,document, "@return", "Returns", elementName + ":Returns");
      renderParamTag(page,document, "@throws", "Throws", elementName + ":Throws");
      renderTag(page,document, "@see", "See Also", elementName + ":See Also");
      renderTag(page,document, "@since", "Since", elementName + ":Since");
      renderTag(page,document, "@version", "Version", elementName + ":Version");
   }
   
   private void log(String string)
   {
       System.out.println(string);
   }

   public void renderTag(StringBuilder page, Doc documentP, String tagName, String logicalName, String elementName)
   {
       log("renderTag: "+elementName);
      // print tags
      Tag tags[] = documentP.tags(tagName);
      for (int tagIdx = 0; tagIdx < tags.length; tagIdx++)
      {
         if (tagIdx == 0)
         {
            page.append(_dia.nl(_dia.b( logicalName )));
         }

         renderParagraph(page, tags[tagIdx].text(), elementName);
         page.append(_dia.nl(""));
      }
   }

   public void renderParamTag(StringBuilder page, Doc document, String tagName, String logicalName, String elementName)
   {
       log("renderParamTag: "+elementName);
      // print tags
      Tag tags[] = document.tags(tagName);
      
      
      for (int i = 0; i < tags.length; i++)
      {
         if (i == 0)
         {
            page.append(_dia.h3(logicalName));
         }

         int separator = tags[i].text().indexOf(" ");
         String paramName = tags[i].text();
         String ending = "";
         if (separator >= 0)
         {
            paramName = tags[i].text().substring(0, separator)+ ": ";
            ending = tags[i].text().substring(separator);
         }
        
         page.append(_dia.i(paramName) );
         renderParagraph(page, ending, elementName);
         page.append(_dia.nl(""));
      }
   }

   public void renderElement(StringBuilder page, ProgramElementDoc element)
   {
      if (element instanceof ExecutableMemberDoc)
      {
         ExecutableMemberDoc member = (ExecutableMemberDoc) element;

         String returnType = " ";
         if (member instanceof MethodDoc)
         {
            MethodDoc method = (MethodDoc) member;
            Type aType = method.returnType();
            if (null != aType)
            {
               returnType = " " + aType.qualifiedTypeName() + aType.dimension() + " ";
            }
         }
         page.append(_dia.nl("> "+ element.modifiers() + returnType + _dia.b(element.name())));
         

         Parameter[] parameters = member.parameters();
         for (int parNo = 0; parNo < parameters.length; parNo++)
         {
            if (parNo != 0)
            {
               page.append(", ");
            }
            Type thisType = parameters[parNo].type();

            page.append(_dia.i(thisType.typeName() + thisType.dimension() + " " + parameters[parNo].name()));
         }
         page.append(_dia.nl(""));
      }
      else
      {
         page.append(_dia.nl(" "+ element.modifiers() + _dia.b(element.name() )));
      }
      page.append(_dia.nl(""));
   }

   private void renderParagraph(StringBuilder page, String text, String which)
   {

        page.append(text);
      
   }

   private boolean isToBePrinted(Doc d)
   {
       return true;
   }
}
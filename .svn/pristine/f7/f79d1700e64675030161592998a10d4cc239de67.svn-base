<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:param name="openedPage"></xsl:param>
    <xsl:param name="ctx"></xsl:param>
    

    <xsl:template match="/">
        <div id="menu">
            
        <ul id="dhtmlgoodies_tree2" class="dhtmlgoodies_tree">
            <xsl:variable name="id"></xsl:variable>
            <li id="node0" noDrag="true" noSiblings="true" ><a href="#" onmouseover="showLinks('link_{id}')">Produktkategorier</a>
            <span id="link_{id}" style="display:none">
                     <a href="#" onclick="addChild('{id}')">Adder kategori</a>
                 <span id="category_form_{id}">
                     
                 </span>    
                     
                </span>
                <ul>
                    <xsl:apply-templates select="cats/category"/>
                </ul>
                <span id="addForm_{id}"></span>

            </li>
        </ul>
       
        </div>        
 </xsl:template>

    <xsl:template match="category">
        <xsl:variable name="id"><xsl:value-of select="id"/></xsl:variable>
        <li id="{id}">
            <a href="#" onmouseover="showLinks('link_{id}')">
                <span id="category_title_{id}">
                <xsl:value-of select="title"/>
                </span>
                
            </a> 
            <span id="link_{id}" style="display:none">
                   <a href="#" onclick="edit('{id}')">Rediger</a> 
                    <!--<a href="#" onclick="deactivate('{id}')">Slet</a>--> 
                     <a href="#" onclick="addChild('{id}')">Adder underkategori</a>
                     <!--<a href="#" onclick="addBook('{id}')">Adder bog her</a>-->
                     <a href="{$ctx}/category/Category.action?view&amp;category={id}">Vis siden</a>
                 <span id="category_form_{id}">
                     
                 </span>    
                     
                </span>
            <xsl:apply-templates select="children" />
            <span id="addForm_{id}"></span>
        </li>
    </xsl:template>

    <xsl:template match="children">
        <ul>
            <xsl:apply-templates select="category"/>
            <xsl:apply-templates select="book"/>
        </ul>
    </xsl:template>
    
    <xsl:template match="book">
        <xsl:variable name="id"><xsl:value-of select="id"/></xsl:variable>
        <li id="book_{id}" noDrag="true">
            <a href="#" onmouseover="showLinks('book_link_{id}')">
                <span id="book_title_{id}">
                <xsl:value-of select="title"/>
                </span>
                
            </a> 
            <span id="book_link_{id}" style="display:none">
                   <a href="#" onclick="editBook('{id}')">Rediger</a> 
                   <a href="{$ctx}/book/Book.action?page={id}">Vis siden</a>
                 <span id="book_form_{id}">
                     
                 </span>    
                     
                </span>
            <xsl:apply-templates select="children" />            
        </li>
    </xsl:template>




</xsl:stylesheet>
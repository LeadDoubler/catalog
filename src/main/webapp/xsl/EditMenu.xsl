<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:param name="openedPage"></xsl:param>
    <xsl:param name="ctx"></xsl:param>

    <xsl:template match="/">
        <div id="menu">
            
        <ul id="dhtmlgoodies_tree2" class="dhtmlgoodies_tree">
            <li id="node0" noDrag="true" noSiblings="true"><a href="#">Root node</a>
                <ul>
                    <xsl:apply-templates select="page"/>
                </ul>

            </li>
        </ul>
       
        </div>        
</xsl:template>

<xsl:template match="page">
        <xsl:variable name="id"><xsl:value-of select="id"/></xsl:variable>
        <li id="{id}">
            <a href="#" onmouseover="showLinks('link_{id}')">
                <span id="title_{id}">
                <xsl:value-of select="title"/>
                </span>
                
            </a> 
            <span id="link_{id}" style="display:none">
                   <a href="#" onclick="edit('{id}')">Rediger</a> 
                    <!--<a href="#" onclick="deactivate('{id}')">Slet</a>--> 
                     <a href="#" onclick="addChild('{id}')">Adder underside</a>
                     <a href="#" onclick="window.opener.location='../page/Show.action?page={id}'">Vis siden</a>
                 <span id="form_{id}">
                     
                 </span>    
                     
                </span>
            <xsl:apply-templates select="children" />
            <span id="addForm_{id}"></span>
        </li>
</xsl:template>

<xsl:template match="children">
        <ul>
            <xsl:apply-templates select="page"/>
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
                   <a href="book/Book.action?page={id}">Vis siden</a>
                 <span id="book_form_{id}">
                     
                 </span>    
                     
                </span>
            <xsl:apply-templates select="children" />            
        </li>
    </xsl:template>




</xsl:stylesheet>
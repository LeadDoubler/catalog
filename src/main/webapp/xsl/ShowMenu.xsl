<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:param name="openedPage"></xsl:param>
    <xsl:param name="ctx"></xsl:param>
    
    <!--Transforms-->
   <!--
   
   <page>
     <children>    
        <page>   
            <title>Bla</title>
            <id>the id</id>
                <children>
                    <page>...</page>
                </children>
        </page>
     </children>
   </page>
        
   --> 

    <xsl:template match="page">
        <div class="suckertreemenu">
            <ul id="treemenu1">
                <xsl:apply-templates select="children/page" mode="show"/>
            </ul>
            <br style="clear: left;" />
        </div>
    </xsl:template>

    <xsl:template match="page" mode="show">
        <xsl:variable name="id"><xsl:value-of select="id"/></xsl:variable>
        <li id="{id}">                
                <a href="{$ctx}/page/Show.action?page={id}"><xsl:value-of select="title"/></a>                
            <xsl:apply-templates select="children" />            
        </li>
    </xsl:template>

    <xsl:template match="children">
        <ul>
            <xsl:apply-templates select="page" mode="show"/>
        </ul>
    </xsl:template>


    


</xsl:stylesheet>
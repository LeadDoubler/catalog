<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:param name="openedPage"></xsl:param>
    <xsl:param name="ctx"></xsl:param>

    <xsl:template match="/">
            <ul>                      
                 <xsl:apply-templates select="page/children/page"/>
                </ul>
        
 </xsl:template>

    <xsl:template match="page">
        <xsl:variable name="id"><xsl:value-of select="id"/></xsl:variable>
        <li class="item_">
            <a href="{$ctx}/page/Show.action?page={id}">
                <xsl:value-of select="title"/>
            </a>               
        </li>
    </xsl:template>

</xsl:stylesheet>
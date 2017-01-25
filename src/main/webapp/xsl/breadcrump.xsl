<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" />
    <xsl:param name="openedPage">33434</xsl:param>
    <xsl:param name="ctx"></xsl:param>

    <xsl:template match="page">
            <p>
                openedPAge = <xsl:value-of select="$openedPage"/>
                <xsl:apply-templates select="children/page" mode="show"/>
            </p>
    </xsl:template>

   <xsl:template match="page" mode="show">
        <xsl:variable name="id"><xsl:value-of select="id"/></xsl:variable> 
        <xsl:if test="descendant::id.value=$openedPage">
            <a href="{$ctx}/page/Show.action?page={id}"><xsl:value-of select="title"/></a>&gt;                 
            <xsl:apply-templates select="children" />
        </xsl:if>
    </xsl:template>

    <xsl:template match="children">
        
            <xsl:apply-templates select="page" mode="show"/>
       
    </xsl:template>

<xsl:template match="page[descendant::id=$openedPage]" mode="show">
                  <xsl:variable name="id"><xsl:value-of select="id"/></xsl:variable>                      
                  <xsl:apply-templates select="children" />
                      </xsl:template>

</xsl:stylesheet>
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <xsl:param name="openedPage"></xsl:param>

    <xsl:template match="/ul">
        <div id="menu">
        <ul id="menuList">
            <xsl:apply-templates select="li" mode="list"/>
        </ul>
        </div>
 </xsl:template>

    <xsl:template match="li" mode="list">
        <li class="page">
            <xsl:copy-of select="a"/>
        </li>
    </xsl:template>



    <xsl:template match="li[descendant::li/attribute::id=$openedPage]" mode="list">
        <li>
            <xsl:copy-of select="a"/>
            <xsl:apply-templates select="ul" mode="children"/>
        </li>
    </xsl:template>

    <xsl:template match="ul" mode="children">
        <ul id="children_of_{@id}">
               <xsl:apply-templates select="li" mode="list"/>
            </ul>
    </xsl:template>

    <xsl:template match="li[@id=$openedPage]" mode="list">
        <li class="openedPage">
           <strong><xsl:copy-of select="a"/></strong>
           <xsl:apply-templates select="ul" mode="children"/>
        </li>
    </xsl:template>


</xsl:stylesheet>
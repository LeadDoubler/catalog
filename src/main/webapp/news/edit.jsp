<%@ include file="/taglibs.jsp"%>
<stripes:layout-render name="/layout/standard.jsp" title="Rediger nyhed:">
    <stripes:layout-component name="contents">
        <stripes:form action="/news/News.action" focus="" method="post">
            <stripes:hidden name="news" />

            <div>
                <span class="form_left">Tidspunkt:</span>
                <span class="form_right">
                    <stripes:text id="newsDate" name="news.stringDate" size="8"/>
                    <img src="${pageContext.request.contextPath}/jsCalendar/img.gif" id="trigger" style="cursor: pointer;" title="Dato vælger" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"/>
                    <script type="text/javascript">
                    Calendar.setup(
                    {
                          inputField  : "newsDate",    // ID of the input field
                          ifFormat    : "%d/%m-%Y %H:%M",    // the date format
                          button      : "trigger",       // ID of the button
                          showsTime   : true
                    }
                    );
                    </script>   
                </span>
            </div>

            <div>
                <span class="form_left">Titel:</span>
                <span class="form_right"><stripes:text name="news.title"/></span>
            </div>
            
            <cat:in name="news.link" type="text" label="Link"/>
            <div>
                <span class="form_right">
                    Eller intern side:
                    <stripes:select name="news.page">
                        <stripes:options-collection value="id" collection="${page.tree}" label="title" />
                    </stripes:select>
                </span>
            </div>

            <div>
                <span class="form_left">Beskrivelse:</span>
                <span class="form_right"><stripes:textarea name="news.description"/></span>
            </div>
            
            <stripes:submit name="save" value="Gem" class="submit" style="float:right"/>

        </stripes:form>
   </stripes:layout-component>
</stripes:layout-render>
<%-- 
    Document   : inStart
    Created on : 07-05-2012, 16:58:25
    Author     : mortenmatras
--%>

<%@tag description="put the tag description here" pageEncoding="MacRoman"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="label"%>
<%@attribute name="requiredField"%>
<%@attribute name="name"%>

<div class="form-field clear">
    <label for="${name}" class="form-label size-120 fl-space2">

        ${label}:

        <c:if test="${requiredField != null && requiredField == 'true'}"><span class="required">*</span></c:if>

    </label>
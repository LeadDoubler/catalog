<#assign title = "Campingdiner">

<#include "${headerFile}">
			<div style="float: left; border: 3px #4d646a solid; width: 539px;">
				<div style="font:85% Georgia, 'Times New Roman', Times, serif; line-height: 120%;  padding: 10px 0 0 22px;  border-bottom: 3px #4d646a solid;">
					<p><span style="font-weight:bold; color:#4F656D">Ordernumber:</span> ${order.id}</p>
					<p><span style="font-weight:bold; color:#4F656D">Name:</span> Lorem ipsum dolor sit amet</p>
					<p>
					<span style="font-weight:bold; color:#4F656D">Address:</span> &Aring;sumhus ApS, Ryttervej 19,<br /> 
<span style="padding: 0 0 0 65px">5240 Odense N&Oslash;</span></p>
				</div>
				<div>
					<table cellpadding="0" cellspacing="0" style="margin: 24px 0 45px 15px;width: 509px; font: 90% Georgia, 'Times New Roman', Times, serif; border:none ">
						<thead style="background:url(${context}images/mail-table-thead.gif); padding: 0 4px 0 0;font-weight: bold; color:#fff">
							<tr>
								<td style="padding: 6px 0 4px 15px; width: 175px">Antal</td>
								<td style="padding: 6px 0 4px; width: 206px">Vare</td>
								<td style="padding: 6px 0 4px;">Pris</td>								
							</tr>
						</thead>
						<tbody style="color: #666666">
						<#list items as item>
	    					<tr style="background:url(${context}images/mail-table-tbody.gif) repeat-y; padding: 0 4px 0 1px;">
								<td style="padding: 4px 0 4px 15px;">${item.occurences}</td>
								<td style="padding: 4px 0 4px; ">${item.name}</td>
								<td style="padding: 4px 80px 4px 0;  text-align: right">${item.totalprice}</td>
							</tr>
						</#list>
						
						</tbody>
						<tfoot style="background:url(${context}images/mail-table-tfoot.gif) no-repeat; padding: 0 4px 5px 0; font-weight:bold; color: #00A8EE; font-size: 100%">
						<td style="padding: 9px 0 15px 15px;">IALT</td>
						<td colspan="2" style="padding: 9px 80px 15px 0; text-align: right">${shopcart.sum}</td>
						
						</tfoot>
					
					</table>
						
				</div>
				<div style="background: #4e656d; color:#8c9b9e">
					<img src="${context}images/mail-footer.gif" alt="" style="float: left; position:relative; margin: -25px 6px 0 0" />
					<p style="padding:14px 0 0; margin:0; font-size: 140%; line-height: 90%">&Aring;sumhus ApS, Ryttervej 19 <br />
					<span style="font-size: 90%">5240 Odense N&Oslash;, <a href="mailto:kontakt@campingdiner.dk" style="text-decoration:none; color:#8c9b9e; font-size: 80%">kontakt@campingdiner.dk</a></span></p>
					<p style="color:#fff; padding: 7px 11px 10px 160px; margin:0; line-height:100%; font-size: 70%; font-family: Arial, Helvetica, sans-serif">Alt indhold å dette website er copyright 2007, Campingdiner.dk.</p>
				</div>
					
					
				
			
			
				</div>
<#include "${footerFile}">
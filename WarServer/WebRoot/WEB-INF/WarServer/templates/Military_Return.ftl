<span>您的军队${military.name}已经完成作战任务返回城市，具体信息如下：</span>
<table cellpadding="0" cellspacing="0" class="no_nesting">
	<tr>
		<th>派遣</th>
		<td>返回</td>
	</tr>
	<tr>
		<th width="100"><span class="span_green"><b>指挥官</b></span></th>
		<td><span class="span_green"><b>${military.cityHero.name}</b></span></td>
	</tr>
	<#list military.battleArmyList as battleArmy>
	<#if battleArmy?exists>
	<tr>
		<th>${battleArmy.army.name}</th>
		<td>${battleArmy.amount}</td>
	</tr>
	</#if>
	</#list>
	<#if haveResource?exists>
	<tr>
		<th>获得资源</th>
		<td>木材:${woodNum} 钢铁:${steelNum} 石油:${oilNum} 食物:${foodNum} 金钱:${moneyNum}</td>
	</tr>
	</#if>
</table>
<#if winner==0><span class="span_normal">发生在 ${mapName}(${position}) 的战斗已结束，我方未在规定时间内击败对手，战斗失败！</span></#if>
<#if winner==1><span class="span_win">发生在 ${mapName}(${position}) 的战斗已结束，我方获得胜利！</span></#if>
<#if winner==2><span class="span_lose">发生在 ${mapName}(${position}) 的战斗已结束，我方惨遭失败！</span></#if>
<#if winner==3><span class="span_lose">发生在 ${mapName}(${position}) 的战斗已结束，我方落荒而逃！</span></#if>
<br />
<#if winner!=1>由于您的军队作战失败，无法给您带回任何战斗信息。</#if>
<#if winner==1>
战斗自${startTime}开始，共进行${round}回合，持续${lastTime}。
<table cellpadding="0" cellspacing="0">
	<tr>
		<th colspan="2" align="center"><span class="span_yellow">战斗地点(${position})</span></th>
	</tr>
	<tr>
		<th><span class="span_green">进攻方：${attackerName}(我方)</span></th>
		<th><span class="span_red">防守方：${defenderName}(敌方)</span></th>
	</tr>
	<tr>
		<td valign="top" style="padding:2px;">
			<table cellpadding="0" cellspacing="0">
				<tr align="center">
					<td></td>
					<td width="30%">派遣</td>
					<td width="30%">阵亡</td>
				</tr>
				<tr>
					<td><span class="span_green"><b>指挥官</b></span></td>
					<td colspan="2"><span class="span_green"><b>${attackerCommanderName} (等级${attackerCommanderLevel})</b></span></td>
				</tr>
				<#list attackerMilitaryResultList as military>
				<tr>
					<td>${military.name}</td>
					<td align="center">${military.dispatchNum}</td>
					<td align="center">${military.deadNum}</td>
				</tr>
				</#list>
			</table>
		</td>
		<td valign="top" style="padding:2px;">
			<table cellpadding="0" cellspacing="0">
				<tr align="center">
					<td></td>
					<td width="30%">派遣</td>
					<td width="30%">阵亡</td>
				</tr>
				<tr>
					<td><span class="span_red"><b>指挥官</b></span></td>
					<td colspan="2"><span class="span_red"><b>${defenderCommanderName} (等级${defenderCommanderLevel})</b></span></td>
				</tr>
				<#list defenderMilitaryResultList as military>
				<tr>
					<td>${military.name}</td>
					<td align="center">${military.dispatchNum}</td>
					<td align="center">${military.deadNum}</td>
				</tr>
				</#list>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">指挥官获得经验</th>
				<td>${exp}</td>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">获得声望</th>
				<td>${renown}</td>
			</table>
		</td>
	</tr>
	<#if winner==1>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">获得资源</th>
				<td>木材:${woodNum} 钢铁:${steelNum} 石油:${oilNum} 食物:${foodNum} 金钱:${moneyNum}</td>
			</table>
		</td>
	</tr>
	</#if>
	<#if winner==1 && droppedTreasureList?size!=0>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">获得道具</th>
				<td><#list droppedTreasureList as treasure>${treasure.treasure.name} </#list></td>
			</table>
		</td>
	</tr>
	</#if>
	<#if winner==1 && droppedEquipmentList?size!=0>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">获得装备</th>
				<td><#list droppedEquipmentList as equipment>${equipment.equipment.name} </#list></td>
			</table>
		</td>
	</tr>
	</#if>
	<#if winner==1 && droppedTaskItemList?size!=0>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">任务道具</th>
				<td><#list droppedTaskItemList as taskItem>${taskItem.treasure.name}×${taskItem.num} </#list></td>
			</table>
		</td>
	</tr>
	</#if>
</table>
</#if>
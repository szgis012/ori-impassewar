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
			<br />
			<table cellpadding="0" cellspacing="0">
				<tr align="center">
					<td></td>
					<td>参战</td>
					<td>损失</td>
				</tr>
				<#list cityDefenseResultList as cityDefense>
				<tr>
					<td>${cityDefense.name}</td>
					<td align="center">${cityDefense.num}</td>
					<td align="center">${cityDefense.destoryNum}</td>
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
	<#if winner==1 || winner==2>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">掠夺资源</th>
				<td>木材:${woodNum} 钢铁:${steelNum} 石油:${oilNum} 食物:${foodNum} 金钱:${moneyNum}</td>
			</table>
		</td>
	</tr>
	</#if>
	<#if haveColonized!=0>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">殖民</th>
				<td>
					<#if haveColonized==1>
						由于您将对方城市攻陷并且对方城市治安值过低，您已成功殖民该城市。
					</#if>
					<#if haveColonized==2>
						由于您已经殖民该城市，本次战斗未进行任何殖民任务。
					</#if>
					<#if haveColonized==3>
						由于您目前所能殖民的城市数量已达上限，本次战斗未进行任何殖民任务。
					</#if>
				</td>
			</table>
		</td>
	</tr>
	</#if>
	<#if winner==1>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" class="table_row">
				<th width="100">备注</th>
				<td>由于我方战斗胜利，成功降低敌方城市 ${minusSecurity} 点治安值。</td>
			</table>
		</td>
	</tr>
	</#if>
</table>
</#if>
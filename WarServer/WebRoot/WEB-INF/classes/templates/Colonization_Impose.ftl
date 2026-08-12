<#if type==1>
    您已向市长${playerName}的城市${cityName}（${posX}.${posY}）于${month}月${day}日 ${hour}时${minute}分征收物资成功。<br />
本次征收 共获得：<br />
</#if>
<#if type==2>
    市长${playerName}在城市${cityName}（${posX}.${posY}）于${month}月${day}日 ${hour}时${minute}分对您进行了殖民征收。<br />
本次征收 城市共损失：<br />
</#if>
木材：${woodNum}<br />
钢铁：${steelNum}<br />
石油：${oilNum}<br />
食物：${foodNum}<br />
金钱：${moneyNum}<br />
<#if type==1>
征收物资已加入您的城市仓库中，请查收。
</#if>
<#if type==2>
被殖民期结束后，将自动脱离被殖民关系，您可以通过加强城市防御并提高城市治安防止被其他玩家殖民。
</#if>
